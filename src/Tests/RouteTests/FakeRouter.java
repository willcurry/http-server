package Tests.RouteTests;

import Server.Router;
import Server.Routes.BaseRoute;
import Server.Storage;
import Server.Routes.*;

import java.util.ArrayList;

public class FakeRouter extends Router {

    private ArrayList<BaseRoute> routes;

    public FakeRouter(Storage storage) {
        super(storage);
        routes = new ArrayList<>();
        routes.add(new DefaultPageRoute(storage));
        routes.add(new FormRoute(storage));
        routes.add(new MethodOptions2Route());
        routes.add(new MethodOptionsRoute());
        routes.add(new RedirectRoute());
        routes.add(new PatchContentRoute(storage));
        routes.add(new PartialContentRoute(storage));
        routes.add(new TeaRoute());
        routes.add(new CoffeeRoute());
        routes.add(new ParametersRoute());
        routes.add(new PublicFilesRoute(storage));
        routes.add(new EatCookieRoute(storage));
        routes.add(new CookieRoute(storage));
        routes.add(new LogsRoute());
    }


    @Override
    public ArrayList<BaseRoute> allRoutes() {
        return routes;
    }
}
