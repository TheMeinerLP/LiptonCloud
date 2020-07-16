package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.UnRegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;

/**
 * Coded By CryCodes
 * Class: ShutDownHookWrapper
 * Date : 26.06.2020
 * Time : 17:53
 * Project: LiptonCloud
 */

public class ShutDownHookWrapper extends Thread {

    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="ShutDownHookWrapper">
    public ShutDownHookWrapper(LiptonWrapper liptonWrapper){
        this.liptonWrapper = liptonWrapper;
    }
    //</editor-fold>

    //<editor-fold desc="run">
    @Override
    public void run() {
        if (liptonWrapper.isIsrunning()){
            liptonWrapper.getColouredConsoleProvider().info("Stopping Cloud [ShutDownHookWrapper]");
           liptonWrapper.getWrapperMasterClient().sendPacket(new UnRegisterPacket(RegisterType.WRAPPER, new WrapperMeta(false, new WrapperConfig(liptonWrapper.getWrapperConfig().getWrapperID(), liptonWrapper.getWrapperConfig().getHost(), true))));
            if (!this.isInterrupted())
                this.interrupt();
        }
    }
    //</editor-fold>
}
