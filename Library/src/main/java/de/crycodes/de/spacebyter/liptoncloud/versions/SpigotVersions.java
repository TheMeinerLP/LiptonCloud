package de.crycodes.de.spacebyter.liptoncloud.versions;

public enum SpigotVersions {

    SPIGOT_1_8_8("https://cdn.getbukkit.org/spigot/spigot-1.8.8-R0.1-SNAPSHOT-latest.jar", "spigot-1.8.8-R0.1-SNAPSHOT-latest.jar", 1),
    SPIGOT_1_9("https://cdn.getbukkit.org/spigot/spigot-1.9-R0.1-SNAPSHOT-latest.jar", "spigot-1.9-R0.1-SNAPSHOT-latest.jar", 2),
    SPIGOT_1_9_2("https://cdn.getbukkit.org/spigot/spigot-1.9.2-R0.1-SNAPSHOT-latest.jar", "spigot-1.9.2-R0.1-SNAPSHOT-latest.jar", 3),
    SPIGOT_1_9_4("https://cdn.getbukkit.org/spigot/spigot-1.9.4-R0.1-SNAPSHOT-latest.jar", "spigot-1.9.4-R0.1-SNAPSHOT-latest.jar", 4),
    SPIGOT_1_10("https://cdn.getbukkit.org/spigot/spigot-1.10-R0.1-SNAPSHOT-latest.jar", "spigot-1.10-R0.1-SNAPSHOT-latest.jar", 5),
    SPIGOT_1_10_2("https://cdn.getbukkit.org/spigot/spigot-1.10.2-R0.1-SNAPSHOT-latest.jar", "pigot-1.10.2-R0.1-SNAPSHOT-latest.jar", 6),
    SPIGOT_1_11("https://cdn.getbukkit.org/spigot/spigot-1.11.jar", "spigot-1.11.jar", 7),
    SPIGOT_1_11_1("https://cdn.getbukkit.org/spigot/spigot-1.11.1.jar", "spigot-1.11.1.jar", 8),
    SPIGOT_1_11_2("https://cdn.getbukkit.org/spigot/spigot-1.11.2.jar", "spigot-1.11.2.jar", 9),
    SPIGOT_1_12("https://cdn.getbukkit.org/spigot/spigot-1.12.jar", "spigot-1.12.jar", 10),
    SPIGOT_1_12_1("https://cdn.getbukkit.org/spigot/spigot-1.12.1.jar", "spigot-1.12.1.jar", 11),
    SPIGOT_1_12_2("https://cdn.getbukkit.org/spigot/spigot-1.12.2.jar", "spigot-1.12.2.jar", 12),
    SPIGOT_1_13("https://cdn.getbukkit.org/spigot/spigot-1.13.jar", "spigot-1.13.jar", 13),
    SPIGOT_1_13_1("https://cdn.getbukkit.org/spigot/spigot-1.13.1.jar", "spigot-1.13.1.jar", 14),
    SPIGOT_1_13_2("https://cdn.getbukkit.org/spigot/spigot-1.13.2.jar", "pigot-1.13.2.jar", 15),
    SPIGOT_1_14("https://cdn.getbukkit.org/spigot/spigot-1.14.jar", "spigot-1.14.jar", 16),
    SPIGOT_1_14_1("https://cdn.getbukkit.org/spigot/spigot-1.14.1.jar", "spigot-1.14.1.jar", 17),
    SPIGOT_1_14_2("https://cdn.getbukkit.org/spigot/spigot-1.14.2.jar", "spigot-1.14.2.jar", 18),
    SPIGOT_1_14_3("https://cdn.getbukkit.org/spigot/spigot-1.14.3.jar", "spigot-1.14.3.jar", 19),
    SPIGOT_1_14_4("https://cdn.getbukkit.org/spigot/spigot-1.14.4.jar", "spigot-1.14.4.jar", 20),
    SPIGOT_1_15("https://cdn.getbukkit.org/spigot/spigot-1.15.jar", "spigot-1.15.jar", 21),
    SPIGOT_1_15_1("https://cdn.getbukkit.org/spigot/spigot-1.15.1.jar", "spigot-1.15.1.jar", 22),
    SPIGOT_1_15_2("https://cdn.getbukkit.org/spigot/spigot-1.15.2.jar", "spigot-1.15.2.jar", 23),
    SPIGOT_1_16_1("https://cdn.getbukkit.org/spigot/spigot-1.16.1.jar", "spigot-1.16.1.jar", 24);


    private String url;
    private String jarName;
    private int id;

    SpigotVersions(String url, String jarName, int id) {
        this.url = url;
        this.jarName = jarName;
        this.id = id;
    }

    public static SpigotVersions getVersionById(int id){
        for (SpigotVersions spigot_versions : SpigotVersions.values()){
            if (spigot_versions.getId() == id){
                return spigot_versions;
            }
        }
        return null;
    }


    public String getUrl() {
        return url;
    }

    public String getJarName() {
        return jarName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
