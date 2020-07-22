package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.versions.SpigotVersions;

public class InstallCommand extends CloudCommand {

    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="InstallCommand">
    public InstallCommand(String name, String description, String[] aliases, LiptonWrapper wrapper) {
        super(name, description, aliases);
        this.liptonWrapper = wrapper;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("list")) {
                sendVersions(colouredConsoleProvider);
                return true;
            }

            if(isInteger(args[0])) {
                Integer id = Integer.valueOf(args[0]);
                SpigotVersions spigotVersions = SpigotVersions.getVersionById(id);
                liptonWrapper.getVersionsManager().install(spigotVersions);
                return true;
            }
            colouredConsoleProvider.getLogger().error("Please use only the ID's");
            return true;
        }
        sendUsage(colouredConsoleProvider);
        return false;
    }

    public void sendVersions(CloudConsole colouredConsoleProvider){
        colouredConsoleProvider.getLogger().info("Versions you can Install: ");
        for (SpigotVersions spigotVersions : SpigotVersions.values()){
            colouredConsoleProvider.getLogger().info(spigotVersions.getJarName().replace(".jar", "") + " | (§a" + spigotVersions.getId() + "§r)");
        }
    }
    //</editor-fold>

    //<editor-fold desc="isInteger">
    private boolean isInteger(String message){
        try {
            Integer.valueOf(message);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
    //</editor-fold>
    //<editor-fold desc="sendUsage">
    private void sendUsage(CloudConsole colouredConsoleProvider){
        colouredConsoleProvider.getLogger().info("install list");
        colouredConsoleProvider.getLogger().info("install <id>");
    }
    //</editor-fold>

}
