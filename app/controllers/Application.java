package controllers;

import java.util.Set;

import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        Set<String> keys = request.headers.keySet();

        for (String key : keys) {
            System.out.println(key + ":" + request.headers.get(key).value());
        }

        render();
    }

}