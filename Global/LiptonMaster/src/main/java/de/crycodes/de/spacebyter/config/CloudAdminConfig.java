package de.crycodes.de.spacebyter.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: CloudAdminConfig
 * Date : 07.07.2020
 * Time : 12:54
 * Project: LiptonCloud
 */

public class CloudAdminConfig {

    private static Document document;
    private static File configFile = new File("./liptonMaster/cloudAdmins.json");

    public List<String> getList(){
        if (!configFile.exists()){
            document = new Document();
            document.append("ADMINS", new JsonArray());
            document.saveAsConfig(configFile);
        }
        document =  new Document().loadToExistingDocument(configFile);
        List<String> whitelist = new ArrayList<>();
        JsonArray array = document.getArray("ADMINS");
        Gson gson = new Gson();
        for (JsonElement obj : array) {
            whitelist.add(gson.fromJson(obj, String.class));
        }
        document.saveAsConfig(configFile);
        return whitelist;
    }
    public void addPlayer(String name){
        List<String> whitelist = getList();
        if (!whitelist.contains(name)) {
            whitelist.add(name);
            document =  new Document().loadToExistingDocument(configFile);
            document.append("ADMINS", whitelist);
            document.saveAsConfig(configFile);
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + name + "' is now CloudAdmin!");
        } else {
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + name + "' is already an CloudAdmin!");
            return;
        }
    }
    public void removePlayer(String name){
        List<String> whitelist = getList();
        if (whitelist.contains(name)) {
            whitelist.remove(name);
            document =  new Document().loadToExistingDocument(configFile);
            document.append("ADMINS", whitelist);
            document.saveAsConfig(configFile);
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + name + "' is no longer CloudAdmin!");
        } else {
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + name + "' was not found!");
            return;
        }
    }

    public boolean isUserAdmin(String admin){
        List<String> user = getList();
        if (user.contains(admin)){
            return true;
        } else {
            return false;
        }
    }
}
