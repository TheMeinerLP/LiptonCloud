package de.crycodes.de.spacebyter.config;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: FileManager
 * Date : 24.06.2020
 * Time : 11:14
 * Project: LiptonCloud
 */

public class FileManager {

    private final String dir;
    private final String[] roots;

    public FileManager(String dir, String... roots) {
        this.dir = dir;
        this.roots = roots;
    }

    public FileManager create() {
        File dirFile = new File(dir);
        if (!dirFile.exists()) dirFile.mkdirs();
        for (String root : roots) {
            File file = new File(dir + "/" + root);
            if(!file.exists()) {
                file.mkdirs();
                System.out.println("Files created");

            }
        }

        return this;
    }



    /*public FileManager(String dir, String... roots) {
        if (!new File(dir).exists()) new File(dir).mkdirs();
        for (String root : roots) {
            if (!new File(dir + "/" + root).exists())
                new File(dir + "/" + root).mkdirs();
        }

        for (String root : roots) {
            File file = new File(root);
            if(!file.exists()) {
                file.mkdirs();
                System.out.println("Files created");
            }
        }
    }*/
}
