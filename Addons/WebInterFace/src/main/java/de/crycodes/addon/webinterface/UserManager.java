package de.crycodes.addon.webinterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: UserManager
 * Date : 07.07.2020
 * Time : 20:19
 * Project: LiptonCloud
 */

public class UserManager {

    private UserConfig config;

    public UserManager() {
        config = new UserConfig();
    }

    public boolean checkUser(String name, String password){
        String userString = name + "-" + password;
        return getUsers().contains(userString);
    }

    public void addUser(String name, String password){
        this.config.addUser(name, password);
    }
    public void removeUser(String name, String pssword){
        this.config.removePlayer(name ,pssword);
    }

    public List<String> getUsers(){
        return config.getList();
    }

}
