package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.enums.ExitState;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;

import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: AuthResponeHandler
 * Date : 26.06.2020
 * Time : 13:52
 * Project: LiptonCloud
 */

public class AuthResponseHandler extends PacketHandlerAdapter {

    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="AuthResponseHandler">
    public AuthResponseHandler(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof RegisterResponsePacket){
            final RegisterResponsePacket responsePacket = (RegisterResponsePacket) packet;
            if (((RegisterResponsePacket) packet).isAuthenticated())
                liptonWrapper.getColouredConsoleProvider().info("Connected Successful to Master!");
            else
                System.exit(ExitState.TERMINATED.getState());
        }
    }
    //</editor-fold>
}
