package de.crycodes.de.spacebyter.liptoncloud.utils;

/*
 * Created by CryCodes on 11.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.console.enums.Color;

import java.io.IOException;

public class AsciiPrinter {

    public void Print(CloudConsole logger){
        try {
            logger.getLogger().getConsoleReader().clearScreen();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        logger.getLogger().info(".____    .__        __                _________ .__                   .___");
        logger.getLogger().info("|    |   |__|______/  |_  ____   ____ \\_   ___ \\|  |   ____  __ __  __| _/");
        logger.getLogger().info("|    |   |  \\____ \\   __\\/  _ \\ /    \\/    \\  \\/|  |  /  _ \\|  |  \\/ __ | ");
        logger.getLogger().info("|    |___|  |  |_> >  | (  <_> )   |  \\     \\___|  |_(  <_> )  |  / /_/ | ");
        logger.getLogger().info("|_______ \\__|   __/|__|  \\____/|___|  /\\______  /____/\\____/|____/\\____ | ");
        logger.getLogger().info("        \\/  |__|                    \\/        \\/                       \\/ ");
        logger.getLogger().info("Coded by CryCodes, SpaceByter, ErazeYT & CoderPVP");
        logger.getLogger().info("");
        logger.getLogger().info("   Java-Version: " + PropertiesUtils.JAVA_VERSION);
        logger.getLogger().info("   Os-System: " + PropertiesUtils.OS_NAME);
        logger.getLogger().info("   Os-Architecture: " + PropertiesUtils.OS_ARCH);
        logger.getLogger().info("   UserName: " + PropertiesUtils.USER_NAME);
        logger.getLogger().info("");


    }

}
