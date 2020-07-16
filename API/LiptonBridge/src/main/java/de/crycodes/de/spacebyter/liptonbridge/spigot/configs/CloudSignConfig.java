package de.crycodes.de.spacebyter.liptonbridge.spigot.configs;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 16:53                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public class CloudSignConfig {

    private List<CloudSign> servermetas = new ArrayList<>();

    private File signs = new File("./plugins/signs.json");

    private Document config;

    /**
     * Constructor for SignConfig.java
     * */
    public CloudSignConfig(){
        if (!this.signs.exists()) {
            (config = new Document())
                    .append("GROUPS", new JsonArray())
                    .saveAsConfig(this.signs);
        }
        config = new Document();
        config = new Document("GROUPS").loadToExistingDocument(this.signs);
    }

    public boolean signAlreadyThere(CloudSign signObject){
        for (CloudSign signs : loadSigns()){
            Location location = new Location(Bukkit.getWorld(signs.getWorld()), signs.getX(), signs.getY(), signs.getZ());
            Location ohterlocation = new Location(Bukkit.getWorld(signObject.getWorld()), signObject.getX(), signObject.getY(), signObject.getZ());
            if (location.equals(ohterlocation)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method to load all groups
     * */
    public List<CloudSign> loadSigns() {
        this.config.loadToExistingDocument(signs);

        List<CloudSign> groups = new ArrayList<>();

        if (this.config.contains("GROUPS")) {

            JsonArray array = this.config.getArray("GROUPS");
            Gson gson = new Gson();
            for (JsonElement obj : array) {
                groups.add(gson.fromJson(obj, CloudSign.class));
            }
        }
        return groups;
    }

    /**
     * Method to add a ServerMeta to server-config
     * */
    public void addSign(CloudSign group) {
        List<CloudSign> groups = loadSigns();
        groups.add(group);

        this.config.append("GROUPS", groups);
        this.config.saveAsConfig(this.signs);
    }

    /**
     * Method for search for an ServerMeta.java
     *
     * @param name | Search by name
     * */
    public List<CloudSign> getServerByGroup(String name){
        List<CloudSign> serversigns = new ArrayList<>();
        for (CloudSign serverData : loadSigns()){
            if (serverData.getServerGroupName().equalsIgnoreCase(name)){
                serversigns.add(serverData);
            }
        }
        return serversigns;
    }
}
