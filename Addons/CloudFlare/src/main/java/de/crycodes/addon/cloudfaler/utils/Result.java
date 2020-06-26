package de.crycodes.addon.cloudfaler.utils;

import java.beans.ConstructorProperties;
import java.io.Serializable;

/**
 * Coded By CryCodes
 * Class: Result
 * Date : 26.06.2020
 * Time : 10:13
 * Project: LiptonCloud
 */

public class Result implements Serializable {

    private String id;

    private String email;

    private String token;

    private String name;

    @ConstructorProperties({"id", "email", "token", "name"})
    public Result(String id, String email, String token, String name) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }
}