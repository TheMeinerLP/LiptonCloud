package de.crycodes.addon.signsystem.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.crycodes.addon.signsystem.SignSystem;
import de.crycodes.addon.signsystem.objects.SignObject;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.InfoPacket;

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

    private SignSystem signSystem;

    private File configfile;
    private Document config;
    private final String listkey = "groups";

    public SignSystemConfig(SignSystem signSystem) {
        this.signSystem = signSystem;
         configfile = new File(signSystem.getModuleLocation() + "/signsystem/signs.json");
        if (!this.configfile.exists()){
            (config = new Document("GROUP-CONFIG"))
                    .append(listkey, new JsonArray())
                    .saveAsConfig(configfile);
        }
        config = new Document().loadToExistingDocument(this.configfile);
    }

    public void addSign(SignObject serverGroupMeta){
        List<SignObject> groupMetas = getServerGroups();
        if (getSignByName(serverGroupMeta.getId()) != null) return;
        groupMetas.add(serverGroupMeta);
        this.config.append(listkey, groupMetas);
        this.config.saveAsConfig(configfile);
    }
    public List<SignObject> getServerGroups(){
        List<SignObject> groupMetas = new ArrayList<>();
        if (this.config.contains(listkey)){
            JsonArray array = this.config.getArray(listkey);
            Gson gson = new Gson();
            for (JsonElement jsonElement : array){
                groupMetas.add(gson.fromJson(jsonElement, SignObject.class));
            }
        }
        return groupMetas;
    }

    public SignObject getSignByName(Integer id){
        for (SignObject serverGroupMeta : getServerGroups()){
            if (serverGroupMeta.getId().equals(id)){
                return serverGroupMeta;
            }
        }
        return null;
    }
}
