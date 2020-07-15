package de.crycodes.addon.webinterface.routes;

import de.crycodes.addon.webinterface.WebServer;
import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Coded By CryCodes
 * Class: ServerRoute
 * Date : 08.07.2020
 * Time : 16:22
 * Project: LiptonCloud
 */

public class ServerRoute implements Route {

    @Override
    public Object handle(Request request, Response response) {

        if (!WebServer.hasAccess(request.session())) {
            response.redirect("/login");
            return "Error";
        }

        String site = WebServer.getSite("Servers.html");

        StringBuilder post = new StringBuilder();

        for (ServerMeta serverMeta : LiptonMaster.getInstance().getServerManager().getOnlineServers()){
            String server = "<br>" + serverMeta.getServerName() + "<br> " + serverMeta.getWrapperID() + " <br>";
            post.append(server);
        }
        site = site.replace("[SERVER]", post.toString());

        return site;
    }

}
