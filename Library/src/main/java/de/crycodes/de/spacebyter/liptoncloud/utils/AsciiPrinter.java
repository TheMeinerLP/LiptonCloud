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
            logger.info(Color.BLUE + " __    _     ___   _     ___   __   _     __  _____  ____  _     "  + Color.RESET);
            logger.info(Color.BLUE + "/ /`  | |   / / \\ | | | | | \\ ( (` \\ \\_/ ( (`  | |  | |_  | |\\/| " + Color.RESET);
            logger.info(Color.BLUE + "\\_\\_, |_|__ \\_\\_/ \\_\\_/ |_|_/ _)_)  |_|  _)_)  |_|  |_|__ |_|  | " + Color.RESET);
            logger.info(Color.RED +  "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"  + Color.RESET);
            logger.info(Color.BLUE + "Coded by CryCodes & SpaceByter" + Color.RESET);
            logger.info("");
            return;
        }
        logger.info(" __    _     ___   _     ___   __   _     __  _____  ____  _     ");
        logger.info("/ /`  | |   / / \\ | | | | | \\ ( (` \\ \\_/ ( (`  | |  | |_  | |\\/| ");
        logger.info("\\_\\_, |_|__ \\_\\_/ \\_\\_/ |_|_/ _)_)  |_|  _)_)  |_|  |_|__ |_|  | ");
        logger.info("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        logger.info("Coded by CryCodes & SpaceByter");
        logger.info("");

    }

}
