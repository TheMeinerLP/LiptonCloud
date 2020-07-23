package de.crycodes.module.signmodule.enums;

import java.io.Serializable;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 13:11                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public enum SignState implements Serializable {

    ONLINE(1),
    OFFLINE(2),
    LOADING(3),
    LOBBY(4),
    INGAME(5),
    MAINTENANCE(6),
    UNKNOWN(7);

    private Integer id;

    SignState(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
