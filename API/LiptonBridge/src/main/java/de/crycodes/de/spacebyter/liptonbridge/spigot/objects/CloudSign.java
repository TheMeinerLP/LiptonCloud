package de.crycodes.de.spacebyter.liptonbridge.spigot.objects;

import de.crycodes.de.spacebyter.liptonbridge.spigot.enums.SignState;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

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
    private final String serverGroupName;
    private ServerMeta serverMeta;
    private SignState signState;

    public CloudSign(Location location, String serverGroupName) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = location.getWorld().getName();
        this.serverGroupName = serverGroupName;
        serverMeta = null;
        signState = SignState.UNKNOWN;
    }

    //<editor-fold desc="getServerMeta">
    public ServerMeta getServerMeta() {
        return serverMeta;
    }
    //</editor-fold>

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

    //<editor-fold desc="getServerGroupName">
    public String getServerGroupName() {
        return serverGroupName;
    }
    //</editor-fold>

    //<editor-fold desc="getSignState">
    public SignState getSignState() {
        return signState;
    }
    //</editor-fold>

    //<editor-fold desc="setServerMeta">
    public void setServerMeta(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }
    //</editor-fold>

    //<editor-fold desc="setSignState">
    public void setSignState(SignState signState) {
        this.signState = signState;
    }
    //</editor-fold>
}
