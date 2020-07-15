package de.crycodes.addon.webinterface;

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
 * Class: UserConfig
 * Date : 08.07.2020
 * Time : 17:29
 * Project: LiptonCloud
 */

public class UserConfig {

    private Document document;
    private final File location = new File(WebInterFace.getInstance().getModuleLocation() + "/webinterface/");
    private File configFile = new File(WebInterFace.getInstance().getModuleLocation() + "/webinterface/users.json");

    public List<String> getList(){
        if (!location.exists()) location.mkdirs();
        if (!configFile.exists()){
            document = new Document();
            document.append("MaintenancePlayers", new JsonArray());
            document.saveAsConfig(configFile);
        }
        document =  new Document().loadToExistingDocument(configFile);
        List<String> whitelist = new ArrayList<>();
        JsonArray array = document.getArray("MaintenancePlayers");
        Gson gson = new Gson();
        for (JsonElement obj : array) {
            whitelist.add(gson.fromJson(obj, String.class));
        }
        document.saveAsConfig(configFile);
        return whitelist;
    }
    public void addUser(String name, String password){
        String userString = name + "-" + password;
        List<String> whitelist = getList();
        if (!whitelist.contains(userString)) {
            whitelist.add(userString);
            document =  new Document().loadToExistingDocument(configFile);
            document.append("MaintenancePlayers", whitelist);
            document.saveAsConfig(configFile);
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + userString + "' is now in WebUser List!");
        } else {
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + userString + "' is already in WebUser List!");
            return;
        }
    }
    public void removePlayer(String name, String password){
        String userString = name + "-" + password;
        List<String> whitelist = getList();
        if (whitelist.contains(userString)) {
            whitelist.remove(userString);
            document =  new Document().loadToExistingDocument(configFile);
            document.append("MaintenancePlayers", whitelist);
            document.saveAsConfig(configFile);
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + userString + "' is no longer in WebUser List!");
        } else {
            LiptonMaster.getInstance().getColouredConsoleProvider().info("The user '" + userString + "' was not found!");
            return;
        }
    }

}
