package Tests;

import Server.Logger;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoggerTests {
    @Test
    public void logsRequests() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/logs", "");
        Logger.log(fakeRequest);
        assertThat(Logger.getLogs().get(0), is("GET /logs HTTP/1.1"));
    }
}
