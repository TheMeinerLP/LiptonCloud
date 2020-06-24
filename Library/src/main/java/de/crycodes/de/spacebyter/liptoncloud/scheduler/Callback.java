package de.crycodes.de.spacebyter.liptoncloud.scheduler;

/*
 * Created by CryCodes on 14.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


public interface Callback<C> {

    void call(C value);

}