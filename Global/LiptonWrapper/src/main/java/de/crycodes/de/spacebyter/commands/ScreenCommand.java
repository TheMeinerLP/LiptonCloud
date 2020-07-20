package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.screen.Screen;
import de.crycodes.de.spacebyter.screen.ScreenPrinter;

import java.io.IOException;

/**
 * Coded By CryCodes
 * Class: ScreenCommand
 * Date : 20.07.2020
 * Time : 13:37
 * Project: LiptonCloud
 */

public class ScreenCommand extends CloudCommand {

    private final LiptonWrapper liptonWrapper;
    private final ScreenPrinter screenPrinter;

    public ScreenCommand(String name, String description, LiptonWrapper liptonWrapper, ScreenPrinter screenPrinter, String... aliases) {
        super(name, description, aliases);
        this.liptonWrapper = liptonWrapper;
        this.screenPrinter = screenPrinter;
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {

        if (args.length == 1){

            final String subject = args[0];

            if (subject.equalsIgnoreCase("close")){

                if (this.screenPrinter.isInScreen()){

                    colouredConsoleProvider.info("You Quieted the Screen '" + this.screenPrinter.quitCurrentScreen() + "'!");

                    return true;

                }

                colouredConsoleProvider.info("You are not in a Screen Session!");

                return true;
            }
            if (subject.equalsIgnoreCase("list")){

                liptonWrapper.getScreenManager().getScreenConcurrentHashMap().forEach((s, screen) -> {
                    colouredConsoleProvider.sendMessageWithCustomPrefix("§cSCREEN: §r" ,s );
                });

                return true;
            }

            sendUsage(colouredConsoleProvider);

            return true;
        }
        if (args.length == 2){

            final String subject = args[0];
            final String serverName = args[1];

            if (subject.equalsIgnoreCase("open")){

                if (liptonWrapper.getScreenManager().getScreenByName(serverName) != null){

                    final Screen screen = liptonWrapper.getScreenManager().getScreenByName(serverName);

                    this.screenPrinter.create(screen);
                    this.screenPrinter.printLines();

                    return true;
                }

                colouredConsoleProvider.error("Server was not Found!");
                return true;
            }

            sendUsage(colouredConsoleProvider);

            return true;
        }

        sendUsage(colouredConsoleProvider);

        return true;
    }

    private void sendUsage(ColouredConsoleProvider colouredConsoleProvider){
        colouredConsoleProvider.info("screen <open> <server>");
        colouredConsoleProvider.info("screen <close>");
        colouredConsoleProvider.info("screen <list>");
    }
}
