package de.crycodes.de.spacebyter.liptoncloud.config;

import com.google.gson.JsonElement;

import java.io.File;
import java.util.Set;

/**
 * Coded By CryCodes
 * Class: DocumentAbstract
 * Date : 24.06.2020
 * Time : 11:12
 * Project: LiptonCloud
 */

public interface DocumentAbstract {

    <T extends DocumentAbstract> T append(String paramString1, String paramString2);

    <T extends DocumentAbstract> T append(String paramString, Number paramNumber);

    <T extends DocumentAbstract> T append(String paramString, Boolean paramBoolean);

    <T extends DocumentAbstract> T append(String paramString, JsonElement paramJsonElement);

    <T extends DocumentAbstract> T remove(String paramString);

    Set<String> keys();

    String getString(String paramString);

    int getInt(String paramString);

    long getLong(String paramString);

    double getDouble(String paramString);

    boolean getBoolean(String paramString);

    float getFloat(String paramString);

    short getShort(String paramString);

    String convertToJson();

    boolean saveAsConfig(File paramFile);

    boolean saveAsConfig(String paramString);

    <T extends DocumentAbstract> T getDocument(String paramString);
}