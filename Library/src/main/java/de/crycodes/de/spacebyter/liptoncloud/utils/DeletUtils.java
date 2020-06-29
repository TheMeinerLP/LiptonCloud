package de.crycodes.de.spacebyter.liptoncloud.utils;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: DeletUtils
 * Date : 28.06.2020
 * Time : 23:23
 * Project: LiptonCloud
 */

public class DeletUtils {

    /*
     * Right way to delete a non empty directory in Java
     */
    public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirectory(children[i]);
                if (!success) {
                    return false;
                }
            }
        }

        // either file or an empty directory
        return dir.delete();
    }

    /*
     * Incorrect way to delete a directory in Java
     */
    public static void deleteDirectory(String file) {
        File directory = new File(file);
        File[] children = directory.listFiles();
        for (File child : children) {
            System.out.println(child.getAbsolutePath());
        }

        // let's delete this directory
        // it will not work because directory has sub-directory
        // which has files inside it.
        // In order to delete a directory,
        // you need to first delete its files or contents.
        boolean result = directory.delete();
        if (result) {
            directory.getAbsolutePath();
        } else {
            directory.getAbsolutePath();
        }
    }


}
