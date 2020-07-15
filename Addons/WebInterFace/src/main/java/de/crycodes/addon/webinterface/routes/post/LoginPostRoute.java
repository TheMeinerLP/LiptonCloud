package de.crycodes.addon.webinterface.routes.post;

import de.crycodes.addon.webinterface.WebInterFace;
import de.crycodes.addon.webinterface.WebServer;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginPostRoute implements Route{
    @Override
    public Object handle(Request request, Response response) {
        if(WebServer.fails.containsKey(request.host())){
            int fails = WebServer.fails.get(request.host());
            if(fails >= 5){
                response.redirect("/login?blocked");
            }
        }
        boolean result = false;
        String uid = request.queryParams("name");
        String password = request.queryParams("password");

        if(uid != null && password != null){
            result = WebInterFace.getInstance().getUserManager().checkUser(uid, password);
        }
        if(result){
            request.session().attribute("access", true);
            WebServer.fails.put(request.host(), 0);
            response.redirect("http://127.0.0.1:8080");
        } else {
            WebServer.addFail(request.host());
        }
        response.redirect("/login?wrong");
        return result ? "Logged in!" : "Wrong password or username!";
    }
}
