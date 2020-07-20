package de.crycodes.de.spacebyter.liptoncloud.utils;

/**
 * Coded By CryCodes
 * Class: TextTockens
 * Date : 16.04.2020
 * Time : 20:13
 * Project: CrazyCloud
 */

public class TextTockens {

    public String propertiesContent(){
        return "spawn-protection=0\n" +
                "server-name=\n" +
                "generator-settings=\n" +
                "force-gamemode=false\n" +
                "allow-nether=false\n" +
                "gamemode=0\n" +
                "broadcast-console-to-ops=true\n" +
                "enable-query=false\n" +
                "player-idle-timeout=0\n" +
                "difficulty=1\n" +
                "spawn-monsters=true\n" +
                "op-permission-level=0\n" +
                "resource-pack-hash=\n" +
                "announce-player-achievements=false\n" +
                "pvp=true\n" +
                "snooper-enabled=true\n" +
                "level-type=DEFAULT\n" +
                "hardcore=false\n" +
                "enable-command-block=false\n" +
                "max-players=50\n" +
                "network-compression-threshold=256\n" +
                "max-world-size=29999984\n" +
                "server-port=25565\n" +
                "debug=false\n" +
                "server-ip=\n" +
                "spawn-npcs=true\n" +
                "allow-flight=false\n" +
                "level-name=world\n" +
                "view-distance=10\n" +
                "resource-pack=\n" +
                "spawn-animals=true\n" +
                "white-list=false\n" +
                "generate-structures=true\n" +
                "online-mode=false\n" +
                "max-build-height=256\n" +
                "level-seed=\n" +
                "enable-rcon=false\n" +
                "motd=LOBBY";
    }
    public String eulaShit(){
        return "eula=true";
    }

    public String bungeeconfigContent(int port){
        return "player_limit: 550\n" +
                "permissions:\n" +
                "  default: []\n" +
                "  admin:\n" +
                "    - bungeecord.command.alert\n" +
                "    - bungeecord.command.end\n" +
                "    - bungeecord.command.ip\n" +
                "    - bungeecord.command.reload\n" +
                "    - bungeecord.command.send\n" +
                "    - liptoncloud.cloudCommand\n" +
                "    - bungeecord.command.server\n" +
                "    - bungeecord.command.list\n" +
                "timeout: 30000\n" +
                "log_commands: false\n" +
                "online_mode: true\n" +
                "disabled_commands:\n" +
                "  - disabledcommandhere\n" +
                "servers:\n" +
                "  Lobby-1:\n" +
                "    motd: ''\n" +
                "    address: '127.0.0.1:30000'\n" +
                "    restricted: false\n" +
                "listeners:\n" +
                "  - query_port: 25577\n" +
                "    motd: \"\"\n" +
                "    priorities:\n" +
                "      - Lobby-1\n" +
                "    bind_local_address: true\n" +
                "    tab_list: GLOBAL_PING\n" +
                "    query_enabled: false\n" +
                "    host: 0.0.0.0:" + port + "\n" +
                "    forced_hosts:\n" +
                "      pvp.md-5.net: pvp\n" +
                "    max_players: 0\n" +
                "    tab_size: 60\n" +
                "    ping_passthrough: false\n" +
                "    force_default_server: false\n" +
                "ip_forward: true\n" +
                "network_compression_threshold: 256\n" +
                "groups:\n" +
                "  CryCodes:\n" +
                "    - admin\n" +
                "connection_throttle: -1\n" +
                "stats: 13be5ac9-5731-4502-9ccc-c4a80163f14a\n" +
                "prevent_proxy_connections: false";
    }


}
