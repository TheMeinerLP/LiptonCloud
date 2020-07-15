package de.crycodes.addon.webinterface;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.addon.MasterAddon;

/**
 * Coded By CryCodes
 * Class: WebInterFace
 * Date : 07.07.2020
 * Time : 19:51
 * Project: LiptonCloud
 */

public class WebInterFace extends MasterAddon {

    private WebServer webServer;
    private UserManager userManager;

    private static WebInterFace instance;

    @Override
    public void onPrepare() {
        super.onPrepare();
    }

    @Override
    public void onLoading() {
        instance = this;
        userManager = new UserManager();
        webServer = new WebServer().start(LiptonMaster.getInstance().getColouredConsoleProvider(), 8080);

        super.onLoading();
    }

    @Override
    public void onReadyToClose() {
        super.onReadyToClose();
    }

    public static WebInterFace getInstance() {
        return instance;
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
