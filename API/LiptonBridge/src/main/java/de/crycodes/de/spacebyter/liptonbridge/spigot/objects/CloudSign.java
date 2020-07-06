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

    public ServerMeta getServerMeta() {
        return serverMeta;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }

    public String getServerGroupName() {
        return serverGroupName;
    }

    public SignState getSignState() {
        return signState;
    }

    public void setServerMeta(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    public void setSignState(SignState signState) {
        this.signState = signState;
    }
}
