package de.crycodes.de.spacebyter.liptoncloud.utils;

/**
 * Coded By CryCodes
 * Class: MillisUtils
 * Date : 26.06.2020
 * Time : 17:31
 * Project: LiptonCloud
 */

public class MillisUtils {

    public static int getSeconds(Long millis){
        return (int) (millis / 1000) % 60 ;
    }
    public static int getMinutes(Long millis){
        return (int) ((millis / (1000*60)) % 60);
    }
    public static int getHours(Long millis){
        return (int) ((millis / (1000*60*60)) % 24);
    }
    public static int getDays(Long millis){
        return (int) ((millis / (1000*60*60*24)) % 7);
    }
    public static int getWeeks(Long millis){
        return (int) (millis / (1000*60*60*24*7));
    }
    public static int getYears(Long millis){
        return getDays(millis) % 365;
    }

}
