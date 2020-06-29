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
                "motd=CryCloud | CloudServer";
    }
    public String eulaShit(){
        return "eula=true";
    }

    public String propertiesContent(String serverName, Integer port){
        return "spawn-protection=0\n" +
                "server-name=" + serverName + "\n" +
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
                "server-port=" + port + "\n" +
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
                "motd=CryCloud | CloudServer";
    }
    public String bungeeconfigContent(int count){
        return "player_limit: 550\n" +
                "permissions:\n" +
                "  default: []\n" +
                "  admin:\n" +
                "    - bungeecord.command.alert\n" +
                "    - bungeecord.command.end\n" +
                "    - bungeecord.command.ip\n" +
                "    - bungeecord.command.reload\n" +
                "    - bungeecord.command.send\n" +
                "    - bungeecord.command.service\n" +
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
                "    host: 0.0.0.0:" + count + "\n" +
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
                "  CoderPvP:\n" +
                "    - admin\n" +
                "  Noitrix:\n" +
                "    - admin\n" +
                "  LufixYT:\n" +
                "    - admin\n" +
                "  Z_S:\n" +
                "    - admin\n" +
                "connection_throttle: -1\n" +
                "stats: 13be5ac9-5731-4502-9ccc-c4a80163f14a\n" +
                "prevent_proxy_connections: false";
    }
    public String spigotconfigContent(){
        return "# This is the main configuration file for Spigot.\n" +
                "# As you can see, there's tons to configure. Some options may impact gameplay, so use\n" +
                "# with caution, and make sure you know what each option does before configuring.\n" +
                "# For a reference for any variable inside this file, check out the Spigot wiki at\n" +
                "# http://www.spigotmc.org/wiki/spigot-configuration/\n" +
                "#\n" +
                "# If you need help with the configuration or have any questions related to Spigot,\n" +
                "# join us at the IRC or drop by our forums and leave a post.\n" +
                "#\n" +
                "# IRC: #spigot @ irc.spi.gt ( http://www.spigotmc.org/pages/irc/ )\n" +
                "# Forums: http://www.spigotmc.org/\n" +
                "\n" +
                "config-version: 8\n" +
                "messages:\n" +
                "  whitelist: You are not whitelisted on this server!\n" +
                "  unknown-command: Unknown command. Type \"/help\" for help.\n" +
                "  server-full: UpToCloudService | CloudServer is not availble, because it's full\n" +
                "  outdated-client: Outdated client! Please use {0}\n" +
                "  outdated-server: Outdated server! I'm still on {0}\n" +
                "  restart: UpToCloudService | CloudServer restarting\n" +
                "settings:\n" +
                "  save-user-cache-on-stop-only: false\n" +
                "  bungeecord: false\n" +
                "  late-bind: false\n" +
                "  sample-count: 12\n" +
                "  player-shuffle: 0\n" +
                "  filter-creative-items: true\n" +
                "  user-cache-size: 1000\n" +
                "  int-cache-limit: 1024\n" +
                "  moved-wrongly-threshold: 0.0625\n" +
                "  moved-too-quickly-threshold: 100.0\n" +
                "  timeout-time: 60\n" +
                "  restart-on-crash: true\n" +
                "  restart-script: ./start.sh\n" +
                "  netty-threads: 4\n" +
                "  attribute:\n" +
                "    maxHealth:\n" +
                "      max: 2048.0\n" +
                "    movementSpeed:\n" +
                "      max: 2048.0\n" +
                "    attackDamage:\n" +
                "      max: 2048.0\n" +
                "  debug: false\n" +
                "timings:\n" +
                "  enabled: true\n" +
                "  verbose: true\n" +
                "  server-name-privacy: false\n" +
                "  hidden-config-entries:\n" +
                "    - database\n" +
                "    - settings.bungeecord-addresses\n" +
                "  history-interval: 300\n" +
                "  history-length: 3600\n" +
                "commands:\n" +
                "  tab-complete: 0\n" +
                "  log: true\n" +
                "  spam-exclusions:\n" +
                "    - /skill\n" +
                "  silent-commandblock-console: false\n" +
                "  replace-commands:\n" +
                "    - setblock\n" +
                "    - summon\n" +
                "    - testforblock\n" +
                "    - tellraw\n" +
                "stats:\n" +
                "  disable-saving: false\n" +
                "  forced-stats: {}\n" +
                "world-settings:\n" +
                "  default:\n" +
                "    verbose: true\n" +
                "    mob-spawn-range: 4\n" +
                "    anti-xray:\n" +
                "      enabled: true\n" +
                "      engine-mode: 1\n" +
                "      hide-blocks:\n" +
                "        - 14\n" +
                "        - 15\n" +
                "        - 16\n" +
                "        - 21\n" +
                "        - 48\n" +
                "        - 49\n" +
                "        - 54\n" +
                "        - 56\n" +
                "        - 73\n" +
                "        - 74\n" +
                "        - 82\n" +
                "        - 129\n" +
                "        - 130\n" +
                "      replace-blocks:\n" +
                "        - 1\n" +
                "        - 5\n" +
                "    nerf-spawner-mobs: false\n" +
                "    growth:\n" +
                "      cactus-modifier: 100\n" +
                "      cane-modifier: 100\n" +
                "      melon-modifier: 100\n" +
                "      mushroom-modifier: 100\n" +
                "      pumpkin-modifier: 100\n" +
                "      sapling-modifier: 100\n" +
                "      wheat-modifier: 100\n" +
                "      netherwart-modifier: 100\n" +
                "    entity-activation-range:\n" +
                "      animals: 32\n" +
                "      monsters: 32\n" +
                "      misc: 16\n" +
                "    entity-tracking-range:\n" +
                "      players: 48\n" +
                "      animals: 48\n" +
                "      monsters: 48\n" +
                "      misc: 32\n" +
                "      other: 64\n" +
                "    ticks-per:\n" +
                "      hopper-transfer: 8\n" +
                "      hopper-check: 8\n" +
                "    hopper-amount: 1\n" +
                "    random-light-updates: false\n" +
                "    save-structure-info: true\n" +
                "    max-bulk-chunks: 10\n" +
                "    max-entity-collisions: 8\n" +
                "    dragon-death-sound-radius: 0\n" +
                "    seed-village: 10387312\n" +
                "    seed-feature: 14357617\n" +
                "    hunger:\n" +
                "      walk-exhaustion: 0.2\n" +
                "      sprint-exhaustion: 0.8\n" +
                "      combat-exhaustion: 0.3\n" +
                "      regen-exhaustion: 3.0\n" +
                "    max-tnt-per-tick: 100\n" +
                "    max-tick-time:\n" +
                "      tile: 50\n" +
                "      entity: 50\n" +
                "    item-despawn-rate: 6000\n" +
                "    merge-radius:\n" +
                "      item: 2.5\n" +
                "      exp: 3.0\n" +
                "    arrow-despawn-rate: 1200\n" +
                "    enable-zombie-pigmen-portal-spawns: true\n" +
                "    wither-spawn-sound-radius: 0\n" +
                "    view-distance: 10\n" +
                "    hanging-tick-frequency: 100\n" +
                "    zombie-aggressive-towards-villager: true\n" +
                "    chunks-per-tick: 650\n" +
                "    clear-tick-list: false";
    }

}
