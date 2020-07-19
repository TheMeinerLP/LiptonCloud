package de.crycodes.de.spacebyter.liptoncloud.console;

import me.tongfei.progressbar.ProgressBar;
import scala.Char;
import scala.Int;

import java.io.IOException;
import java.util.Scanner;

/**
 * Coded By CryCodes
 * Class: AsyncCloudConsole
 * Date : 19.07.2020
 * Time : 18:23
 * Project: LiptonCloud
 */

public abstract class CloudConsole {

    private final Scanner scanner = new Scanner(System.in);

    public Scanner getScanner() {
        return scanner;
    }

    void clearConsole() {

        for (int i = 0; i < 200; i++){
            System.out.println("\n ");
        }

    }

    abstract void warning(String message);
    abstract void debug(String message);
    abstract void error(String message, Exception exception);
    abstract void error(String message);
    abstract void info(String message);
    abstract void sendMessageWithCustomPrefix(String prefix,String message);

    void printProgress(String taskName,Integer tickDelay){
        try {
            for (int x =0 ; x < 101 ; x++) {
                String data = "\r" + "[" + taskName + "] " + getBlocksAfterNumber(x / 10, "#", '[' , ']') + " Percent: " + x + "%";
                System.out.write(data.getBytes());
                Thread.sleep(tickDelay);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void printEmptyLine(){
        System.out.println("\n");
    }

    private String getBlocksAfterNumber(Integer integer, String block, char startChar, char stopChar){

        StringBuilder line = new StringBuilder(startChar + "");

        if (integer == 10) {
            for (int i = 0; i < 41; i++)
                line.append(block);

            line.append(stopChar);

            return line.toString();
        }

        if (integer < 10)
            for (int i = 0; i < integer; i++)
                line.append(block)
                        .append(block)
                        .append(block)
                        .append(block);

        int size = 41 - line.length();

        line.append(">");

        for (int i = 0; i < size; i++)
            line.append(" ");

        line.append(stopChar);

        return line.toString();

    }

}
