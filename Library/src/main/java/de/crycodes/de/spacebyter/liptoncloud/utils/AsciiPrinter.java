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
            logger.info(Color.BLUE + "░█████╗░██╗░░░░░░█████╗░██╗░░░██╗██████╗░" + Color.RESET);
            logger.info(Color.BLUE + "██╔══██╗██║░░░░░██╔══██╗██║░░░██║██╔══██╗" + Color.RESET);
            logger.info(Color.BLUE + "██║░░╚═╝██║░░░░░██║░░██║██║░░░██║██║░░██║" + Color.RESET);
            logger.info(Color.BLUE + "██║░░██╗██║░░░░░██║░░██║██║░░░██║██║░░██║" + Color.RESET);
            logger.info(Color.BLUE + "╚█████╔╝███████╗╚█████╔╝╚██████╔╝██████╔╝" + Color.RESET);
            logger.info(Color.BLUE + "░╚════╝░╚══════╝░╚════╝░░╚═════╝░╚═════╝░" + Color.RESET);
            logger.info("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            logger.info("Coded by CryCodes & SpaceByter");
            logger.info("");
            return;
        }
        logger.info("░█████╗░██╗░░░░░░█████╗░██╗░░░██╗██████╗░" );
        logger.info("██╔══██╗██║░░░░░██╔══██╗██║░░░██║██╔══██╗");
        logger.info("██║░░╚═╝██║░░░░░██║░░██║██║░░░██║██║░░██║");
        logger.info("██║░░██╗██║░░░░░██║░░██║██║░░░██║██║░░██║");
        logger.info("╚█████╔╝███████╗╚█████╔╝╚██████╔╝██████╔╝");
        logger.info("░╚════╝░╚══════╝░╚════╝░░╚═════╝░╚═════╝░");
        logger.info("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        logger.info("Coded by CryCodes & SpaceByter");
        logger.info("");

    }

}
