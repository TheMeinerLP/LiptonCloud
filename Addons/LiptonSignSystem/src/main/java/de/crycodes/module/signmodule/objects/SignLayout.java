package de.crycodes.module.signmodule.objects;

import java.util.ArrayList;
import java.util.List;

public class SignLayout {

    final String[] ONLINE = {"§b§l%server%",  "%state%", "%online%/%max%", "§7[§c§lCLICK§7]"};
    final String[] LOADING = {"§0" , "§cLoading", "§1"};
    final String[] ANIMATION = {"§c⬛⬜⬜", "§c⬛⬛⬜", "§c⬛⬛⬛"};

    //<editor-fold desc="getServerLayoutDefault">
    public List<String> getServerLayoutDefault(){
        List<String> strings = new ArrayList<>();
        strings.add(ONLINE[0]);
        strings.add(ONLINE[1]);
        strings.add(ONLINE[2]);
        strings.add(ONLINE[3]);
        return strings;
    }
    //</editor-fold>

    //<editor-fold desc="getSignAnimationTick">
    public List<String> getSignAnimationTick(int tick){
        List<String> strings = new ArrayList<>();
        switch (tick){
            default:
            case 1:
                strings.add(LOADING[0]);
                strings.add(LOADING[1]);
                strings.add(ANIMATION[0]);
                strings.add(LOADING[2]);
                return strings;
            case 2:
                strings.add(LOADING[0]);
                strings.add(LOADING[1]);
                strings.add(ANIMATION[1]);
                strings.add(LOADING[2]);
                return strings;
            case 3:
                strings.add(LOADING[0]);
                strings.add(LOADING[1]);
                strings.add(ANIMATION[2]);
                strings.add(LOADING[2]);
                return strings;
        }
    }
    //</editor-fold>

}
