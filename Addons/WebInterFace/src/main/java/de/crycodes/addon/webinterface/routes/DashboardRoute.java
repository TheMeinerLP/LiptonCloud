package de.crycodes.addon.webinterface.routes;

import de.crycodes.addon.webinterface.WebInterFace;
import de.crycodes.addon.webinterface.WebServer;
import de.crycodes.de.spacebyter.LiptonMaster;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

/**
 * Coded By CryCodes
 * Class: DashboardRoute
 * Date : 08.07.2020
 * Time : 16:40
 * Project: LiptonCloud
 */

public class DashboardRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String site = WebServer.getSite("Dashboard.html");

        site = site.replace("[SERVERS]", "Servers: " + LiptonMaster.getInstance().getServerManager().getOnlineServers().size());
        site = site.replace("[WRAPPERS]", "Wrappers: " + LiptonMaster.getInstance().getWrapperManager().getWrapperList().size());
        site = site.replace("[PROXYS]", "Proxys: " + LiptonMaster.getInstance().getProxyManager().getGlobalProxyList().size());
        site = site.replace("[USERS]", "WebInterface-User: " + WebInterFace.getInstance().getUserManager().getUsers().size());

        return site;
    }
}
