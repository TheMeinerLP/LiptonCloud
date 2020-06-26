package de.crycodes.addon.signsystem.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.crycodes.addon.signsystem.SignSystem;
import de.crycodes.addon.signsystem.objects.SavedSignLayoutObject;
import de.crycodes.addon.signsystem.objects.SignConfig;
import de.crycodes.addon.signsystem.objects.SignObject;
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

    private SignSystem signSystem;

    private File configfile;
    private Document config;
    private final String listkey = "groups";

    public SignLayoutConfig(SignSystem signSystem) {
        this.signSystem = signSystem;
        configfile = new File(signSystem.getModuleLocation() + "/signsystem/layouts.json");
        if (!this.configfile.exists()){
            (config = new Document("GROUP-CONFIG"))
                    .append(listkey, new JsonArray())
                    .saveAsConfig(configfile);
        }
        config = new Document().loadToExistingDocument(this.configfile);
    }

    public void addSign(SavedSignLayoutObject serverGroupMeta){
        List<SavedSignLayoutObject> groupMetas = getServerGroups();
        if (getSignByName(serverGroupMeta.getId()) != null) return;
        groupMetas.add(serverGroupMeta);
        this.config.append(listkey, groupMetas);
        this.config.saveAsConfig(configfile);
    }
    public List<SavedSignLayoutObject> getServerGroups(){
        List<SavedSignLayoutObject> groupMetas = new ArrayList<>();
        if (this.config.contains(listkey)){
            JsonArray array = this.config.getArray(listkey);
            Gson gson = new Gson();
            for (JsonElement jsonElement : array){
                groupMetas.add(gson.fromJson(jsonElement, SavedSignLayoutObject.class));
            }
        }
        return groupMetas;
    }

    public SavedSignLayoutObject getSignByName(Integer id){
        for (SavedSignLayoutObject serverGroupMeta : getServerGroups()){
            if (serverGroupMeta.getId() == (id)){
                return serverGroupMeta;
            }
        }
        return null;
    }
}