import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HandlerTests {
    private FakeRequest fakeRequest;
    private Handler handler;

    private void setUpFakeRequest(String verb, String uri, String body) {
        handler = new Handler();
        fakeRequest = new FakeRequest(verb, uri, "HTTP/1.1", body);
    }

    @Test
    public void findsDefaultPageCorrectly() throws IOException {
        setUpFakeRequest("GET", "/", "");
        assertThat(Util.makeString(handler.handle(fakeRequest).asByteArray()), containsString("200 OK"));
    }

    @Test
    public void findsFormCorrectly() throws IOException {
        setUpFakeRequest("POST", "/form", "");
        assertThat(Util.makeString(handler.handle(fakeRequest).asByteArray()), containsString("200 OK"));
    }

    @Test
    public void findsMethodOptionsCorrectly() throws IOException {
        setUpFakeRequest("OPTIONS", "/method_options", "");
        assertThat(Util.makeString(handler.handle(fakeRequest).asByteArray()), containsString("ALLOW: GET,HEAD,POST,OPTIONS,PUT"));
    }

    @Test
    public void findsMethodOptions2Correctly() throws IOException {
        setUpFakeRequest("OPTIONS", "/method_options2", "");
        assertThat(Util.makeString(handler.handle(fakeRequest).asByteArray()), containsString("ALLOW: GET,OPTIONS"));
    }

    @Test
    public void findsRedirectCorrectly() throws IOException {
        setUpFakeRequest("GET", "/redirect", "");
        assertThat(Util.makeString(handler.handle(fakeRequest).asByteArray()), containsString("302 Found"));
    }

    @Test
    public void findsPatchContentCorrectly() throws IOException {
        setUpFakeRequest("PATCH", "/patch-content.txt", "");
        assertThat(Util.makeString(handler.handle(fakeRequest).asByteArray()), containsString("204"));
    }

    @Test
    public void returnsFourOhFourRequest() throws IOException {
        setUpFakeRequest("GET", "/404", "");
        assertThat(Util.makeString(handler.handle(fakeRequest).asByteArray()), containsString("404 Not Found"));
    }
}
