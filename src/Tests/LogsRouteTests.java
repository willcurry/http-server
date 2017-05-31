package Tests;

import Routes.LogsRoute;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LogsRouteTests {
    @Test
    public void onlyAppliesToLogs() {
        LogsRoute logsRoute = new LogsRoute();
        assertTrue(logsRoute.appliesTo("/logs"));
        assertFalse(logsRoute.appliesTo("/invalid"));
    }

    @Test
    public void getReturns401ResponseWhenNotAuthorized() throws IOException {
        LogsRoute logsRoute = new LogsRoute();
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/logs", "");
        assertThat(TestUtil.makeString(logsRoute.handleGET(fakeRequest).asByteArray()), containsString("401 Unauthorized"));
    }
}
