package Tests;

import Server.Logger;
import Server.Routes.LogsRoute;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
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
    public void getReturns401ResponseWhenNotAuthorized() throws IOException, Base64DecodingException {
        LogsRoute logsRoute = new LogsRoute();
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/logs", "");
        fakeRequest.setHeaders(new ArrayList<>());
        assertThat(TestUtil.makeString(logsRoute.handleGET(fakeRequest).asByteArray()), containsString("HTTP/1.1 401 Unauthorized"));
    }

    @Test
    public void getReturns200ResponseWhenAuthorized() throws IOException, Base64DecodingException {
        LogsRoute logsRoute = new LogsRoute();
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/logs", "");
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Authorization: Basic YWRtaW46aHVudGVyMg==");
        fakeRequest.setHeaders(headers);
        assertThat(TestUtil.makeString(logsRoute.handleGET(fakeRequest).asByteArray()), containsString("HTTP/1.1 200 OK"));
    }

    @Test
    public void getHasContentOfLogsWhenAuthorized() throws IOException, Base64DecodingException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/", "");
        Logger.log(fakeRequest);
        LogsRoute logsRoute = new LogsRoute();
        fakeRequest = TestUtil.createFakeRequest("GET", "/logs", "");
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Authorization: Basic YWRtaW46aHVudGVyMg==");
        fakeRequest.setHeaders(headers);
        assertThat(TestUtil.makeString(logsRoute.handleGET(fakeRequest).asByteArray()), containsString("GET / HTTP/1.1"));
    }
}
