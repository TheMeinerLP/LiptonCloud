package de.crycodes.addon.webinterface.routes;

import de.crycodes.addon.webinterface.WebServer;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginRoute implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String ret = WebServer.getSite("Login.html");

        if(request.queryParams("wrong") != null ){
            ret = ret.replace("[wrong?]", "Wrong username or password!");
        } else if(request.queryParams("blocked") != null){
            ret = ret.replace("[wrong?]", "You are temporarily blocked!");
        } else {
            ret = ret.replace("[wrong?]", "");
        }
        return ret;
    }
}
