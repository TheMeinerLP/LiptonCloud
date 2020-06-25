package de.crycodes.addon.signsystem.config;

import de.crycodes.addon.signsystem.SignSystem;
import de.crycodes.addon.signsystem.objects.SignConfig;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: SignSystemConfig
 * Date : 25.06.2020
 * Time : 16:25
 * Project: LiptonCloud
 */

public class SignSystemConfig {

    private final SignSystem signSystem;

    private List<SignConfig> globalSigns;
    private Document document;
    private File configFile;

    public SignSystemConfig(SignSystem signSystem) {
        this.signSystem = signSystem;
        globalSigns = new ArrayList<>();
        configFile = new File(signSystem.getModuleLocation() + "./SignSystem/signs.json");
        if (!configFile.exists()) {
            document = new Document();
            document.append("signs", new ArrayList<SignConfig>());
            document.saveAsConfig(configFile);
        }
        document = Document.loadDocument(configFile);
    }

    public void reload(){
        this.globalSigns = document.getObject("signs", ArrayList.class);
    }

    public void addSign(SignConfig signConfig){
        reload();
        List<SignConfig> signConfigs = this.globalSigns;
        signConfigs.add(signConfig);
        document.append("signs", signConfigs );
        document.saveAsConfig(configFile);
    }
}
