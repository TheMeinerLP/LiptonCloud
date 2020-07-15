package de.crycodes.addon.webinterface;

import de.crycodes.addon.webinterface.routes.*;
import de.crycodes.addon.webinterface.routes.post.LoginPostRoute;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import spark.Session;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebServer {

    public static HashMap<String, Integer> fails = new HashMap();

    public WebServer start(ColouredConsoleProvider colouredConsoleProvider, Integer port) {

        colouredConsoleProvider.info("Start Webserver (Port: " + port + ")");
        port(port);

        get("/", (request, response) -> {
            response.redirect("/dashboard");
            return "";
        });
        post("/login", new LoginPostRoute());
        get("/login", new LoginRoute());

        post("/maintenance", new MaintenanceRoute());
        get("/maintenance", new MaintenanceRoute());

        post("/servers", new ServerRoute());
        get("/servers", new ServerRoute());

        post("/dashboard", new DashboardRoute());
        get("/dashboard", new DashboardRoute());

        post("/create", new CreateRoute());
        get("/create", new CreateRoute());

        new Thread("fail-reset"){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(5 * 60 *  1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for(Map.Entry<String, Integer> s : fails.entrySet()){
                        if(s.getValue() <= 0){
                            continue;
                        }
                        fails.put(s.getKey(), s.getValue()-1);
                    }
                }
            }
        }.start();
        return this;
    }
    public static void addFail(String host){
        if(WebServer.fails.containsKey(host)){
            WebServer.fails.put(host, WebServer.fails.get(host) + 1);
        } else {
            WebServer.fails.put(host, 1);
        }
    }

    public static String getSite(String name){
        try {
            InputStream inputStream = WebServer.class.getClassLoader().getResourceAsStream(name);

            String content = IOUtils.toString(inputStream);

            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean hasAccess(Session session) {
        boolean access = false;
        Object o = session.attribute("access");
        if(o != null){
            if(o instanceof Boolean){
                access = (boolean) o;
            }
        }
        return access;
    }
}
