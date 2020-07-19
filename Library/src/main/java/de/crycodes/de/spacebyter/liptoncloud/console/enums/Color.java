package de.crycodes.de.spacebyter.liptoncloud.console.enums;

import scala.Char;

public enum Color {
    
    RED((char) 27 + "[31m"),
    GREEN((char) 27 + "[32m"),
    YELLOW((char) 27 + "[33m"),
    BLUE((char) 27 + "[34m"),
    MAGENTA((char) 27 + "[35m"),
    CYAN((char) 27 + "[36m"),
    GRAY((char) 27 + "[37m"),
    
    BOLD((char) 27 + "[1m"),
    RESET((char) 27 + "[0m"),
    UNDERLINED((char) 27 + "[4m");

    String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}