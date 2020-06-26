package de.crycodes.addon.cloudfaler.config;

import com.google.common.reflect.TypeToken;
import de.crycodes.addon.cloudfaler.CloudFlare;
import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.utils.files.FileUtils;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Coded By CryCodes
 * Class: ConfigLoader
 * Date : 26.06.2020
 * Time : 10:41
 * Project: LiptonCloud
 */

public class ConfigLoader implements Serializable {

    public ConfigLoader() {
        if (!Files.exists(Paths.get(CloudFlare.getInstance().getModuleLocation() + "/cloudflare/config.json"))) {
            FileUtils.createDirectory(Paths.get(CloudFlare.getInstance().getModuleLocation() + "/cloudflare"));
            new Document()
                    .append("config", new CloudFlareConfig(
                            "someone@example.com",
                            "omccklp3hsgqltnq83zvatyjga5dzmndums96",
                            "example.com",
                            new CloudFlareConfig.CloudFlareZone(
                                    false,
                                    "lozwnnhn4thm3xvjca3jx4q6fkho9ezp94fkw"
                            ), Collections.singletonList(
                            new CloudFlareConfig.CloudFlareGroup(
                                    "Proxy",
                                    "proxy-01"
                            )
                    )
                    )).saveAsConfig(CloudFlare.getInstance().getModuleLocation() + "/cloudflare/config.json");
        }
    }

    public CloudFlareConfig load() {
        return Document.load((CloudFlare.getInstance().getModuleLocation() + "/cloudflare/config.json"))
                .getObject("config", CloudFlareConfig.class);
    }
}