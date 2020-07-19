package de.crycodes.de.spacebyter.liptoncloud.addon;

import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: ModuleService
 * Date : 17.07.2020
 * Time : 11:35
 * Project: LiptonCloud
 */

public final class ModuleService {

    private CloudModule[] modules;
    private final File moduleDir;
    private ModuleManager moduleManager;
    private final ColouredConsoleProvider colouredConsoleProvider;

    private List<LoadedModule> activeModules;

    //<editor-fold desc="ModuleService">
    public ModuleService(File moduleDir, ColouredConsoleProvider colouredConsoleProvider) {
        activeModules = new ArrayList<>();
        this.moduleDir = moduleDir;
        this.colouredConsoleProvider = colouredConsoleProvider;
        modules = null;
    }
    //</editor-fold>

    //<editor-fold desc="loadModules">
    public boolean loadModules(){
        modules = null;
        try {

            this.activeModules.clear();
            this.moduleManager = new ModuleManager();
            this.modules = moduleManager.initAsPlugin(moduleManager.loadDirectory(moduleDir, "config.cfg"));
            if (modules.length == 0) {
                colouredConsoleProvider.info("No Modules found to load!");
                return true;
            }

            for (File file : moduleManager.getModules(moduleDir)){
                HashMap<String, String> data = moduleManager.getOptionsByModule(file,"config.cfg");

                String moduleName = data.getOrDefault("name", "no name defined!");
                String version = data.getOrDefault("version", "no version defined!");
                String author = data.getOrDefault("author", "no author defined!");
                String website = data.getOrDefault("website", "no website defined!");
                String dependencies = data.getOrDefault("dependencies", "no dependencies defined or needed!");

                final LoadedModule loadedModule = new LoadedModule(moduleName,version, author, website,dependencies);
                this.activeModules.add(loadedModule);

                colouredConsoleProvider.info("Loaded Module: " + moduleName + " version: " + version + " author: " + author + " website: " + website + " dependencies: " + dependencies);
            }

            return true;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //</editor-fold>

    //<editor-fold desc="startModules">
    public void startModules(){
        for (int i=0; i < modules.length; i++) {
            modules[i].onEnable();
        }
    }
    //</editor-fold>

    //<editor-fold desc="stopModules">
    public void stopModules(){
        for (int i=0; i < modules.length; i++) {
            modules[i].onDisable();
        }
    }
    //</editor-fold>



    //<editor-fold desc="getActiveModules">
    public List<LoadedModule> getActiveModules() {
        return activeModules;
    }
    //</editor-fold>
}
