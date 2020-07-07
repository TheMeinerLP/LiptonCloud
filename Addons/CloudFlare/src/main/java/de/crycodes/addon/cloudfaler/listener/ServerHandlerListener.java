package de.crycodes.addon.cloudfaler.listener;

import de.crycodes.addon.cloudfaler.CloudFlare;
import de.crycodes.de.spacebyter.liptoncloud.event.Listener;
import de.crycodes.de.spacebyter.liptoncloud.event.enums.EventTargetType;
import de.crycodes.de.spacebyter.liptoncloud.event.events.CloudStartedEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StartProxyEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StartServerEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StopProxyEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StopServerEvent;

/**
 * Coded By CryCodes
 * Class: ServerHandlerListener
 * Date : 07.07.2020
 * Time : 12:49
 * Project: LiptonCloud
 */

public class ServerHandlerListener extends Listener {

    public ServerHandlerListener(String name) {
        super(name);
    }

    @Override
    public void handel(CloudStartedEvent event) { }

    @Override
    public void handel(StartServerEvent event) {
        CloudFlare.getInstance().getCloudFlareUtil().createClientEntry(event.getServerMeta());
    }

    @Override
    public void handel(StopServerEvent event) {
        CloudFlare.getInstance().getCloudFlareUtil().deleteClientEntry(event.getServerMeta());
    }

    @Override
    public void handel(StartProxyEvent event) {
        CloudFlare.getInstance().getCloudFlareUtil().createProxyEntry(event.getProxyMeta());
    }

    @Override
    public void handel(StopProxyEvent event) {
        CloudFlare.getInstance().getCloudFlareUtil().deleteProxyEntry(event.getProxyMeta());
    }
}
