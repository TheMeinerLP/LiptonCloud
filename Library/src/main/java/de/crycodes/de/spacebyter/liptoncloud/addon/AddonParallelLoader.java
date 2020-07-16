package de.crycodes.de.spacebyter.liptoncloud.addon;


import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.configuration.AddonClassConfig;
import de.crycodes.de.spacebyter.liptoncloud.addon.loader.AddonMainClassLoader;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class AddonParallelLoader extends AddonLoader implements Serializable {

    private Queue<JavaAddon> javaAddons = new ConcurrentLinkedDeque<>();

    private final String path;

    public AddonParallelLoader(String path) {
        this.path = path;
    }

    @Override
    public void loadAddons() {
        Collection<AddonClassConfig> addonClassConfigs = this.checkForAddons();

        addonClassConfigs.forEach(addonClassConfig -> {
            try {
                final AddonMainClassLoader addonMainClassLoader = new AddonMainClassLoader(
                    addonClassConfig);
                JavaAddon javaAddon = addonMainClassLoader.loadAddon();
                javaAddon.setAddonMainClassLoader(addonMainClassLoader);

                javaAddons.add(javaAddon);

            } catch (final Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void enableAddons() {
        this.javaAddons.forEach(consumer -> {
            consumer.onLoading();

        });
    }

    @Override
    public void disableAddons() {
        if (javaAddons.isEmpty()) {
            return;
        }

        do {
            JavaAddon consumer = javaAddons.poll();
            consumer.onDisable();

        } while (!javaAddons.isEmpty());
    }

    @Override
    public boolean disableAddon(final String name) {
        JavaAddon javaAddon = this.javaAddons
            .stream()
            .filter(addon -> addon.getAddonName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
        if (javaAddon == null) {
            return false;
        }

        javaAddon.onDisable();

        this.javaAddons.remove(javaAddon);
        return true;
    }

    @Override
    public boolean enableAddon(final String name) {
        Set<AddonClassConfig> addonClassConfigs = new HashSet<>();

        File[] files = new File(path+"/addons").listFiles(pathname ->
            pathname.isFile()
                && pathname.exists()
                && pathname.getName().endsWith(".jar")
                && pathname.getName().replace(".jar", " ").equalsIgnoreCase(name));
        if (files == null || files.length == 0) {
            return false;
        }

        for (File file : files) {
            if (!file.getName().replace(".jar", " ").equalsIgnoreCase(name)) {
                continue;
            }

            try (JarFile jarFile = new JarFile(file)) {
                JarEntry jarEntry = jarFile.getJarEntry("addon.properties");
                if (jarEntry == null) {
                    throw new IllegalStateException(
                        new FileNotFoundException("Could't find properties file"));
                }

                try (InputStreamReader reader = new InputStreamReader(
                    jarFile.getInputStream(jarEntry), StandardCharsets.UTF_8)) {
                    Properties properties = new Properties();
                    properties.load(reader);
                    AddonClassConfig addonClassConfig = new AddonClassConfig(file,
                        properties.getProperty("name"),
                        properties.getProperty("version"),
                        properties.getProperty("mainClazz"));
                    addonClassConfigs.add(addonClassConfig);
                }
            } catch (final Throwable throwable) {
               throwable.printStackTrace();
            }
        }

        addonClassConfigs.forEach(addonClassConfig -> {
            try {
                final AddonMainClassLoader addonMainClassLoader = new AddonMainClassLoader(
                    addonClassConfig);
                JavaAddon javaAddon = addonMainClassLoader.loadAddon();
                javaAddon.setAddonMainClassLoader(addonMainClassLoader);

                javaAddons.add(javaAddon);

                ColouredConsoleProvider.getGlobal().info("Loaded Addon: " + javaAddon.getAddonName());
            } catch (final Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        return true;
    }

    @Override
    public boolean isAddonEnabled(final String name) {
        return this.javaAddons
            .stream()
            .anyMatch(addon -> addon.getAddonName().equalsIgnoreCase(name));
    }

    private Set<AddonClassConfig> checkForAddons() {
        Set<AddonClassConfig> addonClassConfigs = new HashSet<>();

        File[] files = new File(path).listFiles(pathname ->
            pathname.isFile()
                && pathname.exists()
                && pathname.getName().endsWith(".jar"));
        if (files == null) {
            return addonClassConfigs;
        }

        for (File file : files) {
            //REMOVE
            LiptonLibrary.getInstance().getColouredConsoleProvider().info("Loaded Addon: " + file.getName().replace(".jar", ""));
            //REMOVE
            try (JarFile jarFile = new JarFile(file)) {
                JarEntry jarEntry = jarFile.getJarEntry("addon.properties");
                if (jarEntry == null) {
                    throw new IllegalStateException(
                        new FileNotFoundException("Could't find properties file"));
                }

                try (InputStreamReader reader = new InputStreamReader(
                    jarFile.getInputStream(jarEntry), StandardCharsets.UTF_8)) {
                    Properties properties = new Properties();
                    properties.load(reader);
                    AddonClassConfig addonClassConfig = new AddonClassConfig(file,
                        properties.getProperty("name"),
                        properties.getProperty("version"),
                        properties.getProperty("mainClazz"));
                    addonClassConfigs.add(addonClassConfig);
                }
            } catch (final Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        return addonClassConfigs;
    }

    @Override
    public Queue<JavaAddon> getJavaAddons() {
        return this.javaAddons;
    }
}
