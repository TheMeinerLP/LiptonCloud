package de.crycodes.de.spacebyter.liptoncloud.auth;

import de.crycodes.de.spacebyter.liptoncloud.utils.CallBack;


import java.io.*;
import java.util.UUID;

/**
 * Coded By CryCodes
 * Class: KeyGenerator
 * Date : 19.07.2020
 * Time : 12:05
 * Project: LiptonCloud
 */

public class KeyGenerator {

    private final File saltKey;

    public KeyGenerator(File saltKey) {
        this.saltKey = saltKey;
    }

    public void create(CallBack<String> callBack, Integer strength){
        callBack.accept(addHashToHash(getUUIDafterAmount(strength), strength));
    }

    private String getUUIDafterAmount(Integer amount){

        String uuidKey = "";

        for (int i = 0; i < amount; i++){

            uuidKey += UUID.randomUUID().toString();

        }

        return uuidKey;
    }

    private String stringToHash(String value) {
        String hashSalt = UUID.randomUUID().toString();

        if(saltKey.exists()){
            boolean found = false;
            try {

                FileReader fileReader = new FileReader(saltKey);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line = "";

                while((line = bufferedReader.readLine()) != null) {
                    if(line.startsWith("salty=") && (line.split("=").length == 2)){
                        found = true;
                        hashSalt = line.split("=")[1];
                    }
                }

                if(found == false){
                    saltKey.createNewFile();

                    PrintWriter wr = new PrintWriter(saltKey);

                    wr.write("salty=" + hashSalt);
                    wr.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                saltKey.createNewFile();

                PrintWriter wr = new PrintWriter(saltKey);

                wr.write("salty=" + hashSalt);
                wr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //  0123456789
        String[] alpha = "-.,#+^°§=+@".split("");
        String ret = "";

        int saltkey = 0;

        for(String saltchar : hashSalt.split("")){

            char salty = saltchar.charAt(0);

            saltkey += (salty * salty);

        }

        for(String cCurr : value.split("")){

            char cChar = cCurr.charAt(0);

            int end = cChar * cChar * value.length() * (ret.length() + 2) * (alpha.length + 9) + saltkey;

            for(String loopChars : String.valueOf(end).split("")){

                int index = Integer.parseInt(loopChars.replace("-", "9"));

                ret += alpha[index];

            }

        }

        return ret;
    }

    private String addHashToHash(String string, Integer amount){

        String currentHash = string;

        StringBuilder key = new StringBuilder();

        for (int i = 0; i < amount; i++){

            String result = stringToHash(currentHash);

            currentHash = result;

            key.append(result);

        }

        return key.toString();
    }

}
