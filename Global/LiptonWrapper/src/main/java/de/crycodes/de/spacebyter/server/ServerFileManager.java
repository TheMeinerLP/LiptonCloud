package de.crycodes.de.spacebyter.server;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

import java.io.File;

public class ServerFileManager {

    private final File serverLocation = new File("liptonWrapper/server/");
    private final File templateLocation = new File("liptonWrapper/templates/");

    private File serverDir;
    private File templateDir;
    private File serverGroupDir;
    private ServerMeta serverMeta;
}
