package de.crycodes.de.spacebyter.network.keyauth;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

/**
 * Coded By CryCodes
 * Class: KeyManager
 * Date : 31.05.2020
 * Time : 12:52
 * Project: Networking-Framework
 */

public class KeyManager implements KeyManagerInterface {

    private final File keylocation;

    public KeyManager(File keylocation) {
        this.keylocation = keylocation;
    }

    @Override
    public String getKey() {
        try {
            FileInputStream fileInputStream = new FileInputStream(keylocation);
            Scanner scanner = new Scanner(fileInputStream);
            String resultkey = scanner.nextLine();
            scanner.close();
            return resultkey;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "NULL";
        }
    }

    @Override
    public boolean isKey(String key) {
        return this.getKey().equals(key);
    }
    @Override
    public boolean writekey(String key) {
        try {
            FileWriter writer = new FileWriter(keylocation);
            writer.write(key);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String generateKey() {
        return (UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString()).replace("-", "");
    }


}
