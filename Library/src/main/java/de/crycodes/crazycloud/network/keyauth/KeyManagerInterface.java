package de.crycodes.crazycloud.network.keyauth;

public interface KeyManagerInterface {

    public String getKey();
    public boolean isKey(String key);
    public String generateKey();
    public boolean writekey(String key);

}
