package de.crycodes.de.spacebyter.liptoncloud.config;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Coded By CryCodes
 * Class: ConfigurationAdapter
 * Date : 26.06.2020
 * Time : 11:01
 * Project: LiptonCloud
 */



public final class ConfigurationAdapter extends TypeAdapter<Configuration> implements Serializable {

    private final Lock writeLock = new ReentrantLock();

    @Override
    public void write(JsonWriter jsonWriter, Configuration configuration) throws IOException {
        writeLock.lock();

        try {
            write0(jsonWriter, configuration);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Configuration read(JsonReader jsonReader) throws IOException {
        JsonElement jsonElement = TypeAdapters.JSON_ELEMENT.read(jsonReader);
        return jsonElement != null && jsonElement.isJsonObject() ?
                new Configuration(jsonElement.getAsJsonObject()) : null;
    }

    private void write0(JsonWriter jsonWriter, Configuration configuration) throws IOException {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, ofNullable(configuration));
    }

    private JsonObject ofNullable(Configuration configuration) {
        return configuration == null ? new JsonObject() :
                configuration.getJsonObject();
    }
}
