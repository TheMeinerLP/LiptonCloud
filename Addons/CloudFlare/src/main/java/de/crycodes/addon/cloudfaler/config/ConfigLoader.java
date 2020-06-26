package de.crycodes.addon.cloudfaler.config;

import de.crycodes.addon.cloudfaler.CloudFlare;
import de.crycodes.de.spacebyter.liptoncloud.config.Configuration;
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
            new Configuration()
                    .addValue("config", new CloudFlareConfig(
                            "someone@example.com",
                            "API-TOKEN",
                            "example.com",
                            new CloudFlareConfig.CloudFlareZone(
                                    false,
                                    "ZONE-ID"
                            ), Collections.singletonList(
                            new CloudFlareConfig.CloudFlareGroup(
                                    "Proxy",
                                    "proxy-01"
                            )
                    )
                    )).write(Paths.get(CloudFlare.getInstance().getModuleLocation() + "/cloudflare/config.json"));
        }
    }

    public CloudFlareConfig load() {
        return Configuration.parse(Paths.get(CloudFlare.getInstance().getModuleLocation() + "/cloudflare/config.json"))
                .getValue("config", CloudFlareConfig.class);
    }
}