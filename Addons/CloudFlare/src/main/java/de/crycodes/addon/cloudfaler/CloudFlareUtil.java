package de.crycodes.addon.cloudfaler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.crycodes.addon.cloudfaler.config.CloudFlareConfig;
import de.crycodes.addon.cloudfaler.config.ConfigLoader;
import de.crycodes.addon.cloudfaler.dns.ADNSDefaultRecord;
import de.crycodes.addon.cloudfaler.enums.RequestMethod;
import de.crycodes.addon.cloudfaler.utils.Result;
import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: CloudFlareUtil
 * Date : 26.06.2020
 * Time : 10:07
 * Project: LiptonCloud
 */

public class CloudFlareUtil implements Serializable {

    private static CloudFlareUtil instance;
    private final LiptonMaster liptonMaster;
    private JsonParser jsonParser = new JsonParser();
    private final Gson gson = new Gson();

    public CloudFlareUtil(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
        if (instance != null) {
            return;
        }

        instance = this;
        this.cloudFlareConfig = new ConfigLoader().load();

        if (this.cloudFlareConfig.getCloudFlareZone().isUseOwn()) {
            this.zoneID = this.cloudFlareConfig.getCloudFlareZone().getZoneID();
        } else {
            String currentZoneID = getZoneID();
            if (currentZoneID == null) {
                liptonMaster.getColouredConsoleProvider().error(
                        "An error occurred in cloudflare addon: Could not find zone id for given domain, please recheck"
                );
                return;
            }

            zoneID = currentZoneID;
        }

        this.createServerEntries();
        this.createProxyEntries();
    }

    private List<Result> results = new LinkedList<>();
    private String zoneID;
    private CloudFlareConfig cloudFlareConfig;

    private String getZoneID() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(
                    "https://api.cloudflare.com/client/v4/zones?name=" + this.cloudFlareConfig.getDomain()
            ).openConnection();
            httpURLConnection.setRequestMethod(RequestMethod.GET.getStringValue());
            httpURLConnection.setRequestProperty("X-Auth-Email", this.cloudFlareConfig.getEmail());
            httpURLConnection.setRequestProperty("X-Auth-Key", this.cloudFlareConfig.getApiToken());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.connect();

            try (InputStream inputStream = httpURLConnection.getResponseCode() < 400
                    ? httpURLConnection.getInputStream()
                    : httpURLConnection.getErrorStream()) {
                JsonObject jsonObject = convertInputStreamToJson(inputStream);
                httpURLConnection.disconnect();
                if (jsonObject.get("success").getAsBoolean()) {
                    JsonArray jsonArray = jsonObject.get("result").getAsJsonArray();
                    if (jsonArray.size() == 0) {
                        liptonMaster.getColouredConsoleProvider().error(
                                "An error occurred in cloudflare addon",
                                new IllegalStateException(jsonObject.toString())
                        );
                        return null;
                    }

                    return jsonArray.get(0).getAsJsonObject().get("id").getAsString();
                } else {
                    liptonMaster.getColouredConsoleProvider().error(
                            "An error occurred in cloudflare addon: Could not find zone id for given domain, please recheck"
                    );
                    return null;
                }
            }
        } catch (final IOException ex) {
            liptonMaster.getColouredConsoleProvider().error(
                    "Error while opening connection",
                    ex
            );
        }

        return null;
    }

    public void shutdown() {
        this.deleteRecords();
    }

    private void createProxyRecord(ADNSDefaultRecord.SRVRecord dnsRecord, ProxyMeta proxyInfo) {
        String json = gson.toJson(dnsRecord);

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(
                    "https://api.cloudflare.com/client/v4/zones/" + zoneID + "/dns_records"
            ).openConnection();
            httpURLConnection.setRequestMethod(RequestMethod.POST.getStringValue());
            httpURLConnection.setRequestProperty("Content-Length", json.getBytes().length + "");
            httpURLConnection.setRequestProperty("X-Auth-Email", this.cloudFlareConfig.getEmail());
            httpURLConnection.setRequestProperty("X-Auth-Key", this.cloudFlareConfig.getApiToken());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            try (DataOutputStream dataOutputStream = new DataOutputStream(
                    httpURLConnection.getOutputStream())) {
                dataOutputStream.writeBytes(json);
                dataOutputStream.flush();
            }

            try (InputStream inputStream = httpURLConnection.getResponseCode() < 400
                    ? httpURLConnection.getInputStream()
                    : httpURLConnection.getErrorStream()) {
                JsonObject jsonObject = convertInputStreamToJson(inputStream);
                if (jsonObject.get("success").getAsBoolean()) {
                    Result result = new Result(
                            jsonObject.get("result").getAsJsonObject().get("id").getAsString(),
                            this.cloudFlareConfig.getEmail(),
                            this.cloudFlareConfig.getApiToken(),
                            proxyInfo.getName()
                    );
                    results.add(result);
                } else if (jsonObject.get("errors") != null && jsonObject.get("errors").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsString().contains("The record already exists.")) {
                    liptonMaster.getColouredConsoleProvider().warning("§cCloudFlare Record for §e" + proxyInfo.getName() + "§c could not be created because it already exists.");
                    return;
                } else {
                    liptonMaster.getColouredConsoleProvider().error(
                            
                            "An error occurred in cloudflare addon",
                            new IllegalStateException(jsonObject.toString())
                    );
                    return;
                }
            }

            httpURLConnection.disconnect();
        } catch (IOException ex) {
            liptonMaster.getColouredConsoleProvider().error(
                    
                    "Error while opening connection",
                    ex
            );
        }
    }

    private void createClientRecord(ADNSDefaultRecord dnsRecord, ServerMeta client) {
        String json = gson.toJson(dnsRecord);

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(
                    "https://api.cloudflare.com/client/v4/zones/" + zoneID + "/dns_records"
            ).openConnection();
            httpURLConnection.setRequestMethod(RequestMethod.POST.getStringValue());
            httpURLConnection.setRequestProperty("Content-Length", json.getBytes().length + "");
            httpURLConnection.setRequestProperty("X-Auth-Email", this.cloudFlareConfig.getEmail());
            httpURLConnection.setRequestProperty("X-Auth-Key", this.cloudFlareConfig.getApiToken());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            try (DataOutputStream dataOutputStream = new DataOutputStream(
                    httpURLConnection.getOutputStream())) {
                dataOutputStream.writeBytes(json);
                dataOutputStream.flush();
            }

            try (InputStream inputStream = httpURLConnection.getResponseCode() < 400
                    ? httpURLConnection.getInputStream()
                    : httpURLConnection.getErrorStream()) {
                JsonObject jsonObject = convertInputStreamToJson(inputStream);
                if (jsonObject.get("success").getAsBoolean()) {
                    Result result = new Result(
                            jsonObject.get("result").getAsJsonObject().get("id").getAsString(),
                            this.cloudFlareConfig.getEmail(),
                            this.cloudFlareConfig.getApiToken(),
                            client.getServerName()
                    );
                    results.add(result);
                } else if (jsonObject.get("errors") != null && jsonObject.get("errors").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsString().contains("The record already exists.")) {
                    this.liptonMaster.getColouredConsoleProvider().warning("§cCloudFlare Record for §e" + client.getServerName() + "§c could not be created because it already exists.");
                    return;
                } else {
                    liptonMaster.getColouredConsoleProvider().error(
                            
                            "An error occurred in cloudflare addon",
                            new IllegalStateException(jsonObject.toString())
                    );
                    return;
                }
            }

            httpURLConnection.disconnect();
        } catch (IOException ex) {
            liptonMaster.getColouredConsoleProvider().error(
                    
                    "Error while opening connection",
                    ex
            );
        }
    }

    private synchronized void deleteRecords() {
        List<Result> results1 = new ArrayList<>(results);
        results1.forEach(this::deleteRecord);
    }

    private synchronized void deleteRecord(Result result) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(
                    "https://api.cloudflare.com/client/v4/zones/" + zoneID + "/dns_records/" + result
                            .getId()
            ).openConnection();
            httpURLConnection.setRequestMethod(RequestMethod.DELETE.getStringValue());
            httpURLConnection.setRequestProperty("X-Auth-Email", result.getEmail());
            httpURLConnection.setRequestProperty("X-Auth-Key", result.getToken());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.connect();

            try (InputStream inputStream = httpURLConnection.getResponseCode() < 400
                    ? httpURLConnection.getInputStream()
                    : httpURLConnection.getErrorStream()) {
                JsonObject jsonObject = convertInputStreamToJson(inputStream);
                if (jsonObject.get("success").getAsBoolean()) {
                    results.remove(result);
                } else {
                    liptonMaster.getColouredConsoleProvider().error(
                            
                            "An error occurred in cloudflare addon",
                            new IllegalStateException(jsonObject.toString())
                    );
                }
            }
        } catch (final IOException ex) {
            liptonMaster.getColouredConsoleProvider().error(
                    
                    "Error while opening connection",
                    ex
            );
        }
    }

    private void createServerEntries() {
        /*TODO: ADD SERVER*/
    }

    private void createProxyEntries() {
        /*TODO: ADD PROXY*/
    }

    public void createClientEntry(ServerMeta client) {
        if (client == null) {
            return;
        }

        Result result = this.find(client.getServerName());
        if (result != null) {
            this.deleteRecord(result);
        }

        ADNSDefaultRecord adnsDefaultRecord = new ADNSDefaultRecord(
                client.getServerName() + "." + this.cloudFlareConfig.getDomain(),
                client.getHost(),
                new JsonObject()
        );
        this.createClientRecord(adnsDefaultRecord, client);
    }

    public void deleteClientEntry(ServerMeta client) {
        if (client == null) {
            return;
        }

        Result result = this.find(client.getServerName());
        if (result != null) {
            this.deleteRecord(result);
        }
    }

    public void createProxyEntry(ProxyMeta proxyInfo) {
        if (proxyInfo == null) {
            return;
        }

        Result result = this.find(proxyInfo.getName());
        if (result != null) {
            this.deleteRecord(result);
        }

        CloudFlareConfig.CloudFlareGroup cloudFlareGroup = this
                .findGroup(proxyInfo.getName());
        if (cloudFlareGroup == null) {
            return;
        }

        ADNSDefaultRecord.SRVRecord srvRecord = new ADNSDefaultRecord.SRVRecord(
                "_minecraft._tcp." + this.cloudFlareConfig.getDomain(),
                "SRV 1 1 127.0.0.1 " + proxyInfo.getName() + "."
                        + this.cloudFlareConfig.getDomain(),
                "_minecraft",
                "_tcp",
                cloudFlareGroup.getSubDomain().startsWith("@") ? this.cloudFlareConfig.getDomain()
                        : cloudFlareGroup.getSubDomain(),
                1,
                1,
                proxyInfo.getId(),
                proxyInfo.getName() + "." + this.cloudFlareConfig.getDomain()
        );
        this.createProxyRecord(srvRecord, proxyInfo);
    }

    public void deleteProxyEntry(ProxyMeta proxyInfo) {
        if (proxyInfo == null) {
            return;
        }

        Result result = this.find(proxyInfo.getName());
        if (result != null) {
            this.deleteRecord(result);
        }
    }

    private Result find(String name) {
        return this.results
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private CloudFlareConfig.CloudFlareGroup findGroup(String name) {
        return this.cloudFlareConfig.getCloudFlareGroups()
                .stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getTargetProxyGroup().equals(name))
                .findFirst()
                .orElse(null);
    }

    private JsonObject convertInputStreamToJson(InputStream inputStream) {
        String input;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        try {
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }

        } catch (final IOException ex) {
            liptonMaster.getColouredConsoleProvider().error(
                    
                    "Error while reading cloudflare response",
                    ex
            );
        }

        return jsonParser.parse(stringBuilder.substring(0)).getAsJsonObject();
    }

    public static CloudFlareUtil getInstance() {
        return instance;
    }
}
