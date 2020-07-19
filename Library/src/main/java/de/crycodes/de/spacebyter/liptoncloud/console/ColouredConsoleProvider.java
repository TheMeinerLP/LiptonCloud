package de.crycodes.de.spacebyter.liptoncloud.console;



import de.crycodes.de.spacebyter.liptoncloud.console.enums.Color;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class ColouredConsoleProvider extends CloudConsole {

    private static final Collection<ColouredConsoleProvider> loggers = new LinkedList<>();
    private final org.apache.log4j.Logger fileLogger;
    private final org.apache.log4j.Logger apacheLogger;

    private final Boolean colorSupport;
    
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" ) {{
        this.setTimeZone( TimeZone.getTimeZone( "Europe/Berlin" ) );
    }};

    public ColouredConsoleProvider(File logsDirectory, Boolean colorSupport) {
        this.colorSupport = colorSupport;
        Date date = new Date( System.currentTimeMillis() );
        File file = new File( logsDirectory.getAbsolutePath() + "/unknown.log" );
        for ( int id = 1; id < Integer.MAX_VALUE; id++ ) {
            file = new File( logsDirectory, this.simpleDateFormat.format( date ) + "-" + id + ".log" );
            if ( file.exists() ) {
                continue;
            }
            break;
        }

        if ( !logsDirectory.exists() ) {
            logsDirectory.mkdir();
        }

        org.apache.log4j.Logger.getRootLogger().setLevel( Level.OFF );
        this.apacheLogger = org.apache.log4j.Logger.getLogger( "CloudSystemLogger" );
        this.fileLogger = org.apache.log4j.Logger.getLogger( "CloudSystemfileLogger" );

        //PatternLayout layout = new PatternLayout( "[%d{HH:mm:ss}] [%t] %m%n" );
        String threadName = Thread.currentThread().getName().equalsIgnoreCase("main") ? "Cloud-Thread" : Thread.currentThread().getName();
        PatternLayout layout = new PatternLayout( "[" + threadName + " | %d{HH:mm:ss}] %m%n" );
        ConsoleAppender consoleAppender = new ConsoleAppender( layout );
        this.apacheLogger.addAppender( consoleAppender );

        try {
            FileAppender fileAppender = new FileAppender( layout, file.getAbsolutePath(), false );
            FileAppender fileAppender2 = new FileAppender( layout, logsDirectory.getAbsolutePath() + "/latest.log", false );
            this.fileLogger.addAppender( fileAppender );
            this.fileLogger.addAppender( fileAppender2 );
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        this.apacheLogger.setLevel( Level.INFO );
        this.fileLogger.setLevel( Level.INFO );


        // Add to Loggerlist
        ColouredConsoleProvider.loggers.add( this );
    }

    /**
     * logs a normal text into the console
     *
     * @param message the message which should print into the console
     */
    public void info( String message ) {
        message = "§bINFO§r: " + message + "§r";

        this.fileLogger.info( this.removeColorCodes( message ) );
        message = this.translateColorCodes( message );

        this.apacheLogger.info( message );
    }

    /**
     * logs a normal text into the console whit a custom prefix
     *
     * @param message the message which should print into the console
     */
    public void sendMessageWithCustomPrefix(String prefix,String message){
        message = prefix + message + "§r";

        this.fileLogger.info( this.removeColorCodes( message ) );
        message = this.translateColorCodes( message );

        this.apacheLogger.info( message );
    }

    /**
     * logs an error text into the console
     *
     * @param message the message which should print into the console
     */
    public void error( String message ) {
        message = "§cERROR§r: " + message + "§r";

        this.fileLogger.info( this.removeColorCodes( message ) );
        message = this.translateColorCodes( message );

        this.apacheLogger.info( message );
    }

    /**
     * logs an error text and exceptiopn into the console
     *
     * @param message the message which should print into the console
     */
    public void error( String message, Exception e ) {
        message = "§cERROR§r: " + message + "§r";

        this.fileLogger.error( this.removeColorCodes( message ), e );
        message = this.translateColorCodes( message );

        //this.getApacheLogger().info( message );
        this.apacheLogger.error( message, e );
    }

    /**
     * logs a debug text into the console
     *
     * @param message the message which should print into the console
     */
    public void debug( String message ) {
        message = "§5DEBUG§r: " + message + "§r";

        this.fileLogger.info( this.removeColorCodes( message ) );
        message = this.translateColorCodes( message );

        this.apacheLogger.info( message );
    }

    /**
     * logs a warning text into the console
     *
     * @param message the message which should print into the console
     */
    public void warning( String message ) {
        message = "§eWARNING§r: " + message + "§r";

        this.fileLogger.info( this.removeColorCodes( message ) );
        message = this.translateColorCodes( message );

        this.apacheLogger.info( message );
    }

    private String translateColorCodes( String message ) {
        if (colorSupport) {
            Map<String, String> replace = new HashMap<String, String>() {{
                this.put( "§a", Color.GREEN.getColor() );
                this.put( "§b", Color.CYAN.getColor() );
                this.put( "§c", Color.RED.getColor() );
                this.put( "§d", Color.MAGENTA.getColor() );
                this.put( "§e", Color.YELLOW.getColor() );
                this.put( "§f", Color.RESET.getColor() );

                this.put( "§0", Color.RESET.getColor() );
                this.put( "§1", Color.BLUE.getColor() );
                this.put( "§2", Color.GREEN.getColor() );
                this.put( "§3", Color.CYAN.getColor() );
                this.put( "§4", Color.RED.getColor() );
                this.put( "§5", Color.MAGENTA.getColor() );
                this.put( "§6", Color.YELLOW.getColor() );
                this.put( "§7", Color.GRAY.getColor() );
                this.put( "§8", Color.GRAY.getColor() );
                this.put( "§9", Color.BLUE.getColor() );

                this.put( "§r", Color.RESET.getColor() );
                this.put( "§l", Color.BOLD.getColor() );
                this.put( "§n", Color.UNDERLINED.getColor() );
            }};

            for ( Map.Entry entry : replace.entrySet() ) {
                message = message.replaceAll( (String) entry.getKey(), (String) entry.getValue() );
            }

            return message;
        } else {

            for(String replaces : REPLACE_FIX) {
                if(message.contains(replaces)) {
                    message = message.replace(replaces, "");
                }
            }

            return message;
        }
    }

    private String removeColorCodes(String message ) {
        String[] list = new String[]{
                "a", "b", "c", "d", "e", "f",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "r", "l", "n"
        };
        for ( String colorcode : list ) {
            message = message.replaceAll( "§" + colorcode, "" );
        }

        return message;
    }

    private final String[] REPLACE_FIX = {
            "§c","§6","§e", "§2","§a",
            "§b", "§3", "§1", "§9",
            "§d", "§5", "§f", "§7",
            "§8", "§0", "§l", "§o",
            "§r", "§m", "§n", "§k",
            ""
    };

    @Override
    public Scanner getScanner() {
        return super.getScanner();
    }

    @Override
    void printEmptyLine() {
        super.printEmptyLine();
    }
    
    @Override
    public void clearConsole() {
        super.clearConsole();
    }

    @Override
    void printProgress(String taskName, Integer tickDelay) {
        super.printProgress(taskName, tickDelay);
    }

}