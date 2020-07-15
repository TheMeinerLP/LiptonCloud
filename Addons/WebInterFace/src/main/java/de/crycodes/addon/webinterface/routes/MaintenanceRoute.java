package de.crycodes.addon.webinterface.routes;

import com.sun.scenario.effect.impl.sw.java.JSWPhongLighting_POINTPeer;
import de.crycodes.addon.webinterface.WebInterFace;
import de.crycodes.addon.webinterface.WebServer;
import de.crycodes.de.spacebyter.LiptonMaster;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Coded By CryCodes
 * Class: MaintenanceRoute
 * Date : 08.07.2020
 * Time : 15:27
 * Project: LiptonCloud
 */

public class MaintenanceRoute implements Route {

    @Override
    public Object handle(Request request, Response response) {

        if(!WebServer.hasAccess(request.session())){
            response.redirect("/login");
            return "Error";
        }

        String site = WebServer.getSite("Maintenance.html");

        if (request.queryParams().contains("remove")) {
            String name = request.queryParams("name");
            if (name == null){
                return "ERROR USERNAME FIELD WAS EMPTY";
            }

            LiptonMaster.getInstance().getMaintenanceConfig().removePlayer(name);


        } else if (request.queryParams().contains("add")){
            String name = request.queryParams("name");
            if (name == null){
                return "ERROR USERNAME FIELD WAS EMPTY";
            }

            LiptonMaster.getInstance().getMaintenanceConfig().addPlayer(name);

        } else if (request.queryParams().contains("toggle")){
            LiptonMaster.getInstance().getMasterConfig().switchMaintenance();
        }

        site = site.replace("[STATUS]", "Status: " + LiptonMaster.getInstance().getMasterConfig().isMaintenance());

        StringBuilder userList = new StringBuilder("<br>Users: <br>");
        for (String users : LiptonMaster.getInstance().getMaintenanceConfig().getList())
            userList.append(users + ",");

        site = site.replace("[USER]", userList.toString() + "<br>");

        return site;
    }
}
