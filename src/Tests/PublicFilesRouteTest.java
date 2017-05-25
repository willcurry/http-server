package Tests;

import Routes.PublicFilesRoute;
import Server.Storage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PublicFilesRouteTest  {
    @Test
    public void itAppliesToAllFilesInThePublicFolder() throws IOException {
        Storage storage = new Storage("/Users/willcurry/cob_spec/public_test/");
        PublicFilesRoute publicFilesRoute = new PublicFilesRoute(storage);
        assertTrue(publicFilesRoute.appliesTo("/text-file.txt"));
    }
}
