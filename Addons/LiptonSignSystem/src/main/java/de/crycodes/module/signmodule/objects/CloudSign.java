package de.crycodes.module.signmodule.objects;

import org.bukkit.Location;

import java.io.Serializable;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 16:44                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public class CloudSign implements Serializable {

    private final Integer x;
    private final Integer y;
    private final Integer z;
    private final String world;

    public CloudSign(Location location, String serverGroupName) {
        this.x =  location.getBlockX();
        this.y =  location.getBlockY();
        this.z = location.getBlockZ();
        this.world = location.getWorld().getName();
    }

    //<editor-fold desc="getX">
    public Integer getX() {
        return x;
    }
    //</editor-fold>

    //<editor-fold desc="getY">
    public Integer getY() {
        return y;
    }
    //</editor-fold>

    //<editor-fold desc="getZ">
    public Integer getZ() {
        return z;
    }
    //</editor-fold>

    //<editor-fold desc="getWorld">
    public String getWorld() {
        return world;
    }
    //</editor-fold>

}
