package Server;

import Server.Routes.BaseRoute;

import java.util.ArrayList;

public class Router {
    private ArrayList<BaseRoute> routes;

    public Router(Storage storage) {
        routes = new ArrayList<>();
    }

    public void addRoute(BaseRoute route) {
        routes.add(route);
    }

    public ArrayList<BaseRoute> allRoutes() {
        return routes;
    }
}
