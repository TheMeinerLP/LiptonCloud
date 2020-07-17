package de.crycodes.de.spacebyter.liptoncloud.enums;

public enum ExitState {

        STARTED_AS_ROOT(0),
        STOPPED_SUCESS(1),
        NOT_JAVA_8(2),
        CONTROLLERKEY_MISSING(3),
        TERMINATED(4),
        MASTER_NOT_REACHABLE(5),
        VERSION_UPDATE(6);

        int state;

    ExitState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
