package de.crycodes.addon.webinterface.routes;

import de.crycodes.addon.webinterface.WebInterFace;
import de.crycodes.addon.webinterface.WebServer;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Coded By CryCodes
 * Class: ServerGroupRoute
 * Date : 08.07.2020
 * Time : 16:57
 * Project: LiptonCloud
 */

public class CreateRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        if(!WebServer.hasAccess(request.session())){
            response.redirect("/login");
            return "Error";
        }

        String site = WebServer.getSite("Create.html");

        if (request.queryParams().contains("creategroup")){

            String name = request.queryParams("name");

            Integer mins = Integer.valueOf(request.queryParams("minserver"));
            Integer maxs = Integer.valueOf(request.queryParams("maxserver"));
            Integer minm = Integer.valueOf(request.queryParams("minmemory"));
            Integer maxm = Integer.valueOf(request.queryParams("maxmemory"));
            Boolean dynamic = Boolean.valueOf(request.queryParams("dynamic"));

            if (request.queryParams("maxmemory") == null || request.queryParams("minmemory") == null || request.queryParams("minserver") == null || request.queryParams("maxserver") == null || name == null){
                response.redirect("/create");
                return "ERROR PARAM IS MISSING";
            }

            ServerGroupMeta serverGroupMeta = new ServerGroupMeta(name,maxm,minm,dynamic,false,maxs,mins);

            System.out.println(serverGroupMeta.toString());

            //TODO: CREATE

        } else if (request.queryParams().contains("createwrapper")){

            String name = request.queryParams("autoupdate");
            Boolean autoupdate = Boolean.valueOf(request.queryParams("createwrapper"));

            if (request.queryParams("createwrapper") == null ){
                response.redirect("/create");
                return "ERROR PARAM IS MISSING";
            }

            WrapperConfig wrapperConfig = new WrapperConfig(name, "127.0.0.1", autoupdate);
            WrapperMeta wrapperMeta = new WrapperMeta(false, wrapperConfig);

            System.out.println(wrapperMeta.toString());

            //TODO: CREATE

        }

        return site;
    }
}
