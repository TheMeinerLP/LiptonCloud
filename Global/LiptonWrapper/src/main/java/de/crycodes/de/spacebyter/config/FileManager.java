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

    //<editor-fold desc="FileManager">
    public FileManager(String dir, String... roots) {
        this.dir = dir;
        this.roots = roots;
    }
    //</editor-fold>

    //<editor-fold desc="create">
    public FileManager create() {
        File dirFile = new File(dir);
        if (!dirFile.exists()) dirFile.mkdirs();
        for (String root : roots) {
            File file = new File(dir + "/" + root);
            if(!file.exists()) {
                file.mkdirs();

            }
        }

        return this;
    }
    //</editor-fold>

}
