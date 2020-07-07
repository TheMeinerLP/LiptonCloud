package de.crycodes.de.spacebyter.liptoncloud.utils;

/*
 * Created by CryCodes on 11.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


import de.crycodes.de.spacebyter.liptoncloud.console.Color;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

public class AsciiPrinter {

    public void Print(ColouredConsoleProvider logger, boolean useColor){
        if (useColor){
            logger.info(Color.RED + ".____    .__        __                _________ .__                   .___");
            logger.info(Color.RED + "|    |   |__|______/  |_  ____   ____ \\_   ___ \\|  |   ____  __ __  __| _/");
            logger.info(Color.RED + "|    |   |  \\____ \\   __\\/  _ \\ /    \\/    \\  \\/|  |  /  _ \\|  |  \\/ __ | ");
            logger.info(Color.RED + "|    |___|  |  |_> >  | (  <_> )   |  \\     \\___|  |_(  <_> )  |  / /_/ | ");
            logger.info(Color.RED + "|_______ \\__|   __/|__|  \\____/|___|  /\\______  /____/\\____/|____/\\____ | ");
            logger.info(Color.RED + "        \\/  |__|                    \\/        \\/                       \\/ ");
            logger.info("Coded by CryCodes & SpaceByter");
            logger.info("");
            return;
        }
        logger.info(".____    .__        __                _________ .__                   .___");
        logger.info("|    |   |__|______/  |_  ____   ____ \\_   ___ \\|  |   ____  __ __  __| _/");
        logger.info("|    |   |  \\____ \\   __\\/  _ \\ /    \\/    \\  \\/|  |  /  _ \\|  |  \\/ __ | ");
        logger.info("|    |___|  |  |_> >  | (  <_> )   |  \\     \\___|  |_(  <_> )  |  / /_/ | ");
        logger.info("|_______ \\__|   __/|__|  \\____/|___|  /\\______  /____/\\____/|____/\\____ | ");
        logger.info("        \\/  |__|                    \\/        \\/                       \\/ ");
        logger.info("Coded by CryCodes & SpaceByter");
        logger.info("");

    }

}
