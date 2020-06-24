package de.crycodes.de.spacebyter.liptoncloud.event.utils;
/*
 * Created by CryCodes on 21.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


public abstract class Cancelable {
    public abstract void setCancelled(boolean cancelled);
    public abstract boolean isCancelled();
}
