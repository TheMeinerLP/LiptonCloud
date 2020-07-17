package de.crycodes.de.spacebyter.liptoncloud.addon;

import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.event.Listener;

/**
 * Coded By CryCodes
 * Class: CloudModule
 * Date : 17.07.2020
 * Time : 11:27
 * Project: LiptonCloud
 */

public abstract class CloudModule<T> {

    private T t;

    public void setInstance(T t){
        this.t = t;
    }

    public T getService(){
        return this.t;
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public void registerCommand(CloudCommand cloudCommand){}
    public void registerListener(Listener listener){}

}
