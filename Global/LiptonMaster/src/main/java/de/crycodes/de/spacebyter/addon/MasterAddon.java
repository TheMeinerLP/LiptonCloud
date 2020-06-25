package de.crycodes.de.spacebyter.addon;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.addon.JavaAddon;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.event.Listener;
import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: MasterAddon
 * Date : 25.06.2020
 * Time : 15:26
 * Project: LiptonCloud
 */

public abstract class MasterAddon extends JavaAddon {

    private File config;

    private final File moduleLocation = new File("./liptonMaster/modules/");

     public Document getAddonConfig(){
        this.config = new File("./" + this.getAddonClassConfig().getName() + "/");
        Document document = Document.loadDocument(config);
        return document;
    }
    public void safeAddonConfig(Document document){
        this.config = new File("./" + this.getAddonClassConfig().getName() + "/");
        document.saveAsConfig(config);
    }

    public void registerEvent(Event event){
        LiptonMaster.getInstance().getEventManager().registerEvent(event);
    }
    public void registerListener(Listener listener){
        LiptonMaster.getInstance().getEventManager().registerListener(listener);
    }
    public void registerCommand(CloudCommand cloudCommand){
        LiptonMaster.getInstance().getCommandManager().registerCommand(cloudCommand);
    }

    public File getConfig() {
        this.config = new File("./" + this.getAddonClassConfig().getName() + "/");
        return config;
    }

    public File getModuleLocation() {
        return moduleLocation;
    }
}
