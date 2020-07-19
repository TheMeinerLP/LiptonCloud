package de.crycodes.de.spacebyter.liptoncloud.addon;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Coded By CryCodes
 * Class: AddonManager
 * Date : 17.07.2020
 * Time : 11:27
 * Project: LiptonCloud
 */

public final class ModuleManager {

    //<editor-fold desc="loadClass">
    public Class<?> loadClass(File dir, String config) throws IOException, ClassNotFoundException {
        final JarFile jf = new JarFile(dir);
        final JarEntry je = jf.getJarEntry(config);

        final BufferedReader br = new BufferedReader(new InputStreamReader(jf.getInputStream(je)));

        final HashMap<String, String> data = new HashMap<>();

        String in;
        while((in = br.readLine()) != null) {
            if(in.isEmpty() || in.startsWith("#"))
                continue;

            final String[] split = in.split("=");
            data.put(split[0], split[1]);
        }

        jf.close();

        return Class.forName(data.get("main"), true, new URLClassLoader(new URL[]{dir.toURI().toURL()}));
    }
    //</editor-fold>

    //<editor-fold desc="getOptionsByModule">
    public HashMap<String, String> getOptionsByModule(File dir,String config) throws IOException, ClassNotFoundException {
        final JarFile jf = new JarFile(dir);
        final JarEntry je = jf.getJarEntry(config);

        final BufferedReader br = new BufferedReader(new InputStreamReader(jf.getInputStream(je)));

        final HashMap<String, String> data = new HashMap<>();

        String in;
        while((in = br.readLine()) != null) {
            if(in.isEmpty() || in.startsWith("#"))
                continue;

            final String[] split = in.split("=");
            data.put(split[0], split[1]);
        }
        jf.close();
        return data;
    }
    //</editor-fold>

    //<editor-fold desc="loadDirectory">
    public Class<?>[] loadDirectory(File dir, String config) throws ClassNotFoundException, IOException {
        final File[] files = dir.listFiles();

        final Class<?>[] classes = new Class<?>[files.length];

        for (int i=0; i<files.length; i++)
            classes[i] = loadClass(files[i], config);

        return classes;
    }
    //</editor-fold>

    //<editor-fold desc="getModules">
    public File[] getModules(File dir){
        return dir.listFiles();
    }
    //</editor-fold>

    //<editor-fold desc="initAsPlugin">
    public CloudModule initAsPlugin(Class<?> group) throws InstantiationException, IllegalAccessException {
        return (CloudModule) group.newInstance();
    }
    //</editor-fold>

    //<editor-fold desc="initAsPlugin">
    public CloudModule[] initAsPlugin(Class<?>[] group) throws InstantiationException, IllegalAccessException {
        final CloudModule[] plugins = new CloudModule[group.length];
        for (int i=0; i<group.length; i++)
            plugins[i] = initAsPlugin(group[i]);
        return plugins;
    }
    //</editor-fold>

}
