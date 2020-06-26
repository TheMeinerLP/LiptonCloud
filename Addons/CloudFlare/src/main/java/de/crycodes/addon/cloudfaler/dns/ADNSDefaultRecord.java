package de.crycodes.addon.cloudfaler.dns;

import com.google.gson.JsonObject;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.Serializable;

/**
 * Coded By CryCodes
 * Class: ADNSDefaultRecord
 * Date : 26.06.2020
 * Time : 10:11
 * Project: LiptonCloud
 */

public class ADNSDefaultRecord extends DNSRecord implements Serializable {

    public ADNSDefaultRecord(String name, String content, JsonObject data) {
        super("A", name, content, 1, false, data);
    }

    public static class SRVRecord extends DNSRecord {

        public SRVRecord(String name, String content, String service,
                         String proto, String nameSRV, int priority, int weight, int port, String target) {
            super("SRV", name, content, 1, false, new Document()
                    .append("service", service)
                    .append("proto", proto)
                    .append("name", nameSRV)
                    .append("priority", priority)
                    .append("weight", weight)
                    .append("port", port)
                    .append("target", target).obj()
            );
        }
    }
}