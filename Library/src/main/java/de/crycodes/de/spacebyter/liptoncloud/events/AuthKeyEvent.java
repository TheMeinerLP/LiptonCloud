package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;

/**
 * Coded By ErazeYT
 * Class: AuthKeyEvent
 * Date : 20.07.2020
 * Time : 00:36
 * Project: LiptonCloud3
 */

public class AuthKeyEvent {

    private final Document document;
    private final String string;

    public AuthKeyEvent(Document document, String string) {
        this.document = document;
        this.string = string;
    }

    public Document getDocument() {
        return document;
    }

    public String getString() {
        return string;
    }
}
