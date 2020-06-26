package de.crycodes.addon.cloudfaler.enums;

import java.io.Serializable;

public enum RequestMethod implements Serializable {

    POST("POST"),

    DELETE("DELETE"),

    GET("GET");

    private String stringValue;

    RequestMethod(String value) {
        this.stringValue = value;
    }

    public String getStringValue() {
        return stringValue;
    }
}