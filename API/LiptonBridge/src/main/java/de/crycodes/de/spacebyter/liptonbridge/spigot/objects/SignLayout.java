package de.crycodes.de.spacebyter.liptonbridge.spigot.objects;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 18:22                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public class SignLayout {


    String defaultline1 = "&b&l%server%";
    String defaultline2 = "%state%";
    String defaultline3 = "%online%/%max%";
    String defaultline4 = "&7[&c&lCLICK&7]";

    String loadingline1_0 = "&0";
    String loadingline2_0 = "&cLoading";
    String loadingline3_0 = "&c⬛⬜⬜";
    String loadingline4_0 = "&1";

    String loadingline1_1 = "&0";
    String loadingline2_1 = "&cLoading";
    String loadingline3_1 = "&c⬛⬛⬜";
    String loadingline4_1 = "&1";

    String loadingline1_2 = "&0";
    String loadingline2_2 = "&cLoading";
    String loadingline3_2 = "&c⬛⬛⬛";
    String loadingline4_2 = "&1";


    public List<String> getDefaultLayOut(){
        List<String> strings = new ArrayList<>();
        strings.add(defaultline1);
        strings.add(defaultline2);
        strings.add(defaultline3);
        strings.add(defaultline4);
        return strings;
    }
    public List<String> getloadingDefaultLayOut(int tick){
        List<String> strings = new ArrayList<>();
        switch (tick){
            case 1:
                strings.add(loadingline1_0);
                strings.add(loadingline2_0);
                strings.add(loadingline3_0);
                strings.add(loadingline4_0);
                return strings;
            case 2:
                strings.add(loadingline1_1);
                strings.add(loadingline2_1);
                strings.add(loadingline3_1);
                strings.add(loadingline4_1);
                return strings;
            case 3:
                strings.add(loadingline1_2);
                strings.add(loadingline2_2);
                strings.add(loadingline3_2);
                strings.add(loadingline4_2);
                return strings;
            default:
                strings.add(loadingline1_0);
                strings.add(loadingline2_0);
                strings.add(loadingline3_0);
                strings.add(loadingline4_0);
                return strings;
        }

    }

}
