package de.crycodes.de.spacebyter.liptonbridge.spigot.sign;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.SignGroup;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

import java.io.File;
import java.util.*;


/**
 * Coded By CryCodes
 * Class: Signdocument
 * Date : 20.07.2020
 * Time : 15:21
 * Project: LiptonCloud
 */

public class SignConfig {

    private File signFile = new File("./../../../../resources/signs.json");
    private Document document;
    
    private final String listKey = "signs";

    public SignConfig() {
        if(!(signFile.exists())) {
            document = new Document();
            document.saveAsConfig(signFile);
        }
        document = Document.loadDocument(signFile);
    }

    public void addOrReplace(SignGroup serverGroupMeta){
        this.document = Document.loadDocument(signFile);

        document.append(serverGroupMeta.getName().toUpperCase(), serverGroupMeta);

        document.saveAsConfig(signFile);
    }


    public SignGroup getSignGroupAfterName(String name){
        if (this.document.contains(name.toUpperCase()))
            return document.getObject(name.toUpperCase(), SignGroup.class);

        return new SignGroup(name.toUpperCase());
    }

}
