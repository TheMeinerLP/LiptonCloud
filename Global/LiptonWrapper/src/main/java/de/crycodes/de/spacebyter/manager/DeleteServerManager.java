package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.utils.DeletUtils;
import de.crycodes.de.spacebyter.screen.Screen;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: DeleteServerManager
 * Date : 17.07.2020
 * Time : 15:00
 * Project: LiptonCloud
 */

public class DeleteServerManager {

    private final LiptonWrapper liptonWrapper;

    public DeleteServerManager(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
    }

    public void delete(String serverName) throws IOException {
        Screen screen = liptonWrapper.getScreenManager().getScreenByName(serverName);

        if (screen.getProcess().isAlive())
            screen.getProcess().destroy();
        if (screen.getThread().isAlive())
            screen.getThread().stop(new Throwable());

        File[] subFiles = screen.getServerDir().listFiles();

        for (File file : subFiles){
            if (!file.getName().equalsIgnoreCase("SPIGOT.JAR") || !file.getName().equalsIgnoreCase("LiptonBridge-1.0-SNAPSHOT.jar")) continue;

            if (file.isDirectory())
                FileUtils.deleteDirectory(file);

            if (!file.isDirectory())
                file.delete();

        }


        liptonWrapper.getScreenManager().unregisterScreen(screen);

    }
}
