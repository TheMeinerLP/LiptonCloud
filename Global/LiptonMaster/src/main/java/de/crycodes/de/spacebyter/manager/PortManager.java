package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Coded By CryCodes
 * Class: PortManager
 * Date : 25.06.2020
 * Time : 14:22
 * Project: LiptonCloud
 */

public class PortManager {

    private List<Integer> portlist = new ArrayList<>();
    private final LiptonMaster liptonMaster;

    public PortManager(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }

    public void removePort(Integer port){
        if (this.portlist.contains(port))
            this.portlist.remove(port);
    }

    public int getFreePort(){
        for (int i = 30000; i < 40000; i++){
            if (portlist.contains(i))
                continue;
            else
                portlist.add(i);
                return i;
        }
        return 0;
    }

    public LiptonMaster getLiptonMaster() {
        return liptonMaster;
    }

    public List<Integer> getPortlist() {
        return portlist;
    }
}
