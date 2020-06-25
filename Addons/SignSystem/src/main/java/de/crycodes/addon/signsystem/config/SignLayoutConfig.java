package de.crycodes.addon.signsystem.config;

import de.crycodes.addon.signsystem.SignSystem;
import de.crycodes.addon.signsystem.objects.SavedSignLayoutObject;
import de.crycodes.addon.signsystem.objects.SignConfig;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: SignLayoutConfig
 * Date : 25.06.2020
 * Time : 17:47
 * Project: LiptonCloud
 */

public class SignLayoutConfig {

    private final SignSystem signSystem;

    private List<SavedSignLayoutObject> globalSigns;
    private Document document;
    private File configFile;

    public SignLayoutConfig(SignSystem signSystem) {
        this.signSystem = signSystem;
        globalSigns = new ArrayList<>();
        configFile = new File(signSystem.getModuleLocation() + "./SignSystem/layouts.json");
        if (!configFile.exists()) {
            document = new Document();
            document.append("layouts", new ArrayList<SignConfig>());
            document.saveAsConfig(configFile);
        }
        document = Document.loadDocument(configFile);
    }

    public void reload(){
        this.globalSigns = document.getObject("layouts", ArrayList.class);
    }

    public void addSign(SavedSignLayoutObject signConfig){
        reload();
        List<SavedSignLayoutObject> signConfigs = this.globalSigns;
        signConfigs.add(signConfig);
        document.append("signs", signConfigs );
        document.saveAsConfig(configFile);
    }

    public List<SavedSignLayoutObject> getGlobalSigns() {
        return globalSigns;
    }
}
