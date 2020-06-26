package de.crycodes.addon.signsystem;

import de.crycodes.addon.signsystem.commands.SignLayoutCreateCommand;
import de.crycodes.addon.signsystem.commands.SignReloadCommand;
import de.crycodes.addon.signsystem.config.SignLayoutConfig;
import de.crycodes.addon.signsystem.config.SignSystemConfig;
import de.crycodes.addon.signsystem.network.SignServer;
import de.crycodes.addon.signsystem.objects.SavedSignLayoutObject;
import de.crycodes.addon.signsystem.objects.SignLayout;
import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.addon.MasterAddon;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: SignSystem
 * Date : 25.06.2020
 * Time : 16:15
 * Project: LiptonCloud
 */

public class SignSystem extends MasterAddon {

    private SignSystemConfig signConfig;
    private SignLayoutConfig signLayoutConfig;
    private SignServer signServer;

    private final File configFile = new File(getModuleLocation() + "/SignSystem/config.json");
    private Document document;

    @Override
    public void onLoading() {
        registerCommand(new SignReloadCommand("signreload", "Reload the SignSystem's Config", new String[]{}));
        registerCommand(new SignLayoutCreateCommand("createsignlayout", "Create an SignLayout", new String[]{"createsign", "createlayout"}, this));
        if (!new File(getModuleLocation() + "/SignSystem").exists())new File(getModuleLocation() + "/SignSystem/").mkdirs();
        if (!configFile.exists()){
            LiptonMaster.getInstance().getColouredConsoleProvider().info("[SIGNSYSTEM] CREATED NEW CONFIG");
            document = new Document("SIGN SYSTEM");
            document.append("HOST", "127.0.0.1");
            document.append("PORT", 8899);
            document.saveAsConfig(configFile);
        }
        document = Document.loadDocument(configFile);
        signServer = new SignServer(LiptonMaster.getInstance(), document.getString("HOST"), document.getInt("PORT"));
        signServer.start();
        signConfig = new SignSystemConfig(this);
        signLayoutConfig = new SignLayoutConfig(this);

        if (getLayoutByID(0) == null){
            this.signLayoutConfig.addSign(new SavedSignLayoutObject(0, new SignLayout("Line1", "Line2", "Line3", "Line4")));
        }

        super.onLoading();
    }

    private SavedSignLayoutObject getLayoutByID(int id){
        for (SavedSignLayoutObject layoutObject : signLayoutConfig.getServerGroups()){
            if (layoutObject.getId() == id) return layoutObject;
        }
        return null;
    }


    @Override
    public void onReadyToClose() {
        super.onReadyToClose();
    }

    public SignSystemConfig getSignConfig() {
        return signConfig;
    }

    public SignLayoutConfig getSignLayoutConfig() {
        return signLayoutConfig;
    }
}
