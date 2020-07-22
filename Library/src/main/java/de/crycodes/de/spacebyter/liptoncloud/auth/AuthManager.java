package de.crycodes.de.spacebyter.liptoncloud.auth;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.time.Counter;
import de.crycodes.de.spacebyter.liptoncloud.utils.CallBack;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: AuthManager
 * Date : 19.07.2020
 * Time : 13:08
 * Project: LiptonCloud
 */

public class AuthManager {

    private final File keyFile;
    private final CloudConsole colouredConsoleProvider;

    private KeyGenerator keyGenerator;
    private Counter counter;

    public AuthManager(File keyFile, File slatKeyFile, CloudConsole colouredConsoleProvider) {
        this.keyFile = keyFile;
        this.colouredConsoleProvider = colouredConsoleProvider;
        keyGenerator = new KeyGenerator(slatKeyFile);
        counter = new Counter();
    }

    public void checkKeys(String masterKey, String wrapperKey, CallBack<Boolean> callBack){
        if (masterKey.equals(" ") || wrapperKey.equals(" "))
            callBack.accept(false);
        if (masterKey.equals("null") || wrapperKey.equals("null"))
            callBack.accept(false);

        callBack.accept(masterKey.equalsIgnoreCase(wrapperKey));
    }

    public void createKey(CloudConsole cloudConsole){
        if (!keyFile.exists()){
            counter.start();

            cloudConsole.getLogger().info("Creating new Wrapper Key Please Don`t stop the Master!");

            Document document = new Document("KEYFILE");

            keyGenerator.create(key -> {
                document.append("KEY", key);
            }, 3);

            document.saveAsConfig(keyFile);
            counter.stop();
            counter.printResult("Key Generation",colouredConsoleProvider);
        }
    }

    public String getKey(){
        if (!keyFile.exists()) return "null";

        Document document = Document.loadDocument(keyFile);
        return document.getString("KEY");
    }

}
