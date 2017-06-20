import Server.ExitListener;
import Server.Server;
import Server.ServerManager;
import Server.Router;
import Server.Storage;
import Server.Routes.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Router router = createRouterWithRoutes();
        Server server = new Server(5000, new ExitListener(), new ServerManager(), router);
        server.run();
    }

    private static Router createRouterWithRoutes() {
        Storage storage = new Storage("src/public/");
        Router router = new Router(storage);
        router.addRoute(new DefaultPageRoute(storage));
        router.addRoute(new FormRoute(storage));
        router.addRoute(new MethodOptions2Route());
        router.addRoute(new MethodOptionsRoute());
        router.addRoute(new RedirectRoute());
        router.addRoute(new PatchContentRoute(storage));
        router.addRoute(new PartialContentRoute(storage));
        router.addRoute(new TeaRoute());
        router.addRoute(new CoffeeRoute());
        router.addRoute(new ParametersRoute());
        router.addRoute(new PublicFilesRoute(storage));
        router.addRoute(new EatCookieRoute(storage));
        router.addRoute(new CookieRoute(storage));
        router.addRoute(new LogsRoute());
        return router;
    }
}
