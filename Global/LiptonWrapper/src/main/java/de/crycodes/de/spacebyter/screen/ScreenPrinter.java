package de.crycodes.de.spacebyter.screen;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Coded By CryCodes
 * Class: ScreenPrinter
 * Date : 20.07.2020
 * Time : 13:38
 * Project: LiptonCloud
 */

public class ScreenPrinter {

    private final ColouredConsoleProvider colouredConsoleProvider;
    private final LiptonWrapper liptonWrapper;

    private Screen screen;
    private InputStream inputStream;
    private Scanner reader;
    private boolean isInScreen;

    public ScreenPrinter(ColouredConsoleProvider colouredConsoleProvider, LiptonWrapper liptonWrapper) {
        this.colouredConsoleProvider = colouredConsoleProvider;
        this.liptonWrapper = liptonWrapper;
    }

    public void create(Screen screen){
        this.screen = screen;
    }

    public void printLines(){

        liptonWrapper.getCommandManager().stop();

        liptonWrapper.getScheduler().scheduleAsyncDelay(() -> {

            this.colouredConsoleProvider.info("TEST MESSAGE SCREEN CLOSE");
            isInScreen = false;

            this.reader.close();

            liptonWrapper.getCommandManager().run(colouredConsoleProvider.getScanner());


        }, 4000);

        isInScreen = true;

        final Process process = screen.getProcess();
        inputStream = process.getInputStream();

        reader = new Scanner(inputStream);

        while (isInScreen){

            String line = reader.nextLine();

            if (line == null) return;

            colouredConsoleProvider.sendMessageWithCustomPrefix(
                    "§cSCREEN: §r","[" +  screen.getName() + "]" + line
            );

        }


    }

    public boolean isInScreen(){
        return isInScreen;
    }

    public String quitCurrentScreen() {
        final String endedSession = screen.getName();

        this.isInScreen = false;

        try {
            this.reader.close();
            this.inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return endedSession;
    }

}
