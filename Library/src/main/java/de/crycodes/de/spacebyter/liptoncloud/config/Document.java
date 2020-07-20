package de.crycodes.de.spacebyter.liptoncloud.config;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tareko
 * Repository: https://github.com/CloudNetService/CloudNet
 * Permanent File Link: https://github.com/CloudNetService/CloudNet/blob/b69b94ab38e8d9e227baeaa0182147fcdb08efdb/cloudnet-lib/src/main/java/de/dytanic/cloudnet/lib/utility/document/Document.java
 */

public class Document {

    protected static final Gson GSON = (new GsonBuilder()).serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();
    protected static final JsonParser PARSER = new JsonParser();
    protected String name;
    protected JsonObject dataCatcher;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Document(String name) {
        this.name = name;
        this.dataCatcher = new JsonObject();
    }


    public Document(String name, JsonObject source) {
        this.name = name;
        this.dataCatcher = source;
    }


    public Document(Document defaults) {
        this.dataCatcher = defaults.dataCatcher;
    }


    public Document(Document defaults, String name) {
        this.dataCatcher = defaults.dataCatcher;
        this.name = name;
    }


    public Document() {
        this.dataCatcher = new JsonObject();
    }


    public Document(JsonObject source) {
        this.dataCatcher = source;
    }


    public JsonObject obj() {
        return this.dataCatcher;
    }


    public boolean contains(String key) {
        return this.dataCatcher.has(key);
    }


    public Document append(String key, String value) {
        this.dataCatcher.addProperty(key, value);
        return this;
    }


    public Document append(String key, Number value) {
        this.dataCatcher.addProperty(key, value);
        return this;
    }


    public Document append(String key, Boolean value) {
        this.dataCatcher.addProperty(key, value);
        return this;
    }


    public Document append(String key, JsonElement value) {
        this.dataCatcher.add(key, value);
        return this;
    }


    public Document append(String key, List<String> value) {
        JsonArray jsonElements = new JsonArray();

        for (String b : value) {
            jsonElements.add(b);
        }

        this.dataCatcher.add(key, jsonElements);
        return this;
    }


    public Document append(String key, Document value) {
        this.dataCatcher.add(key, value.dataCatcher);
        return this;
    }


    @Deprecated
    public Document append(String key, Object value) {
        if (value == null) return this;
        this.dataCatcher.add(key, GSON.toJsonTree(value));
        return this;
    }


    public Document remove(String key) {
        this.dataCatcher.remove(key);
        return this;
    }


    public Set<String> keys() {
        Set<String> c = new HashSet<String>();

        for (Map.Entry<String, JsonElement> x : this.dataCatcher.entrySet()) {
            c.add(x.getKey());
        }

        return c;
    }


    public String getString(String key) {
        if (!this.dataCatcher.has(key)) return null;
        return this.dataCatcher.get(key).getAsString();
    }


    public int getInt(String key) {
        if (!this.dataCatcher.has(key)) return 0;
        return this.dataCatcher.get(key).getAsInt();
    }


    public long getLong(String key) {
        if (!this.dataCatcher.has(key)) return 0L;
        return this.dataCatcher.get(key).getAsLong();
    }


    public double getDouble(String key) {
        if (!this.dataCatcher.has(key)) return 0.0D;
        return this.dataCatcher.get(key).getAsDouble();
    }


    public boolean getBoolean(String key) {
        if (!this.dataCatcher.has(key)) return false;
        return this.dataCatcher.get(key).getAsBoolean();
    }


    public float getFloat(String key) {
        if (!this.dataCatcher.has(key)) return 0.0F;
        return this.dataCatcher.get(key).getAsFloat();
    }


    public short getShort(String key) {
        if (!this.dataCatcher.has(key)) return 0;
        return this.dataCatcher.get(key).getAsShort();
    }

    public <T> T getObject(String key, Class<T> class_) {
        if (!this.dataCatcher.has(key)) return null;
        JsonElement element = this.dataCatcher.get(key);

        return (T) GSON.fromJson(element, class_);
    }


    public Document getDocument(String key) {
        return new Document(this.dataCatcher.get(key).getAsJsonObject());
    }


    public JsonArray getArray(String key) {
        return this.dataCatcher.get(key).getAsJsonArray();
    }


    public String convertToJson() {
        return GSON.toJson(this.dataCatcher);
    }


    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this.dataCatcher);
    }


    public boolean saveAsConfig(File backend) {
        if (backend == null) return false;

        if (backend.exists()) {
            backend.delete();
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(backend), "UTF-8")) {

            GSON.toJson(this.dataCatcher, writer);
            return true;
        } catch (IOException ex) {
            ex.getStackTrace();

            return false;
        }
    }


    public boolean saveAsConfig(String path) {
        return saveAsConfig(new File(path));
    }


    public static Document loadDocument(File backend) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(backend), "UTF-8")) {
            JsonObject object = PARSER.parse(new BufferedReader(reader)).getAsJsonObject();
            return new Document(object);
        } catch (Exception ex) {
            ex.getStackTrace();

            return new Document();
        }
    }

    public Document loadToExistingDocument(File backend) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(backend), "UTF-8")) {

            this.dataCatcher = PARSER.parse(reader).getAsJsonObject();

            return this;
        } catch (Exception ex) {
            ex.getStackTrace();

            return new Document();
        }
    }

    public static Document load(String input) {
        try (InputStreamReader reader = new InputStreamReader(new StringBufferInputStream(input), "UTF-8")) {

            return new Document(PARSER.parse(new BufferedReader(reader)).getAsJsonObject());
        } catch (IOException e) {

            e.printStackTrace();

            return new Document();
        }
    }


    public static Document load(JsonObject input) {
        return new Document(input);
    }


    public <T> T getObject(String key, Type t) {
        return (T) GSON.fromJson(this.dataCatcher.get(key), t);
    }
}