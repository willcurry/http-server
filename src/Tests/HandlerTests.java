package Tests;
import Server.Handler;
import Server.Router;
import Server.Storage;
import Tests.RouteTests.FakeRouter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class HandlerTests {
    private Handler handler;

    @Before
    public void before() {
        Storage storage = new Storage("src/public_test/");
        handler = new Handler(new FakeRouter(storage));
    }

    @Test
    public void findsDefaultPageCorrectly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("200 OK"));
    }

    @Test
    public void findsFormCorrectly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("POST", "/form", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("200 OK"));
    }

    @Test
    public void findsMethodOptionsCorrectly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("OPTIONS", "/method_options", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("ALLOW: GET,HEAD,POST,OPTIONS,PUT"));
    }

    @Test
    public void findsMethodOptions2Correctly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("OPTIONS", "/method_options2", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("ALLOW: GET,OPTIONS"));
    }

    @Test
    public void findsRedirectCorrectly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/redirect", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("302 Found"));
    }

    @Test
    public void findsPatchContentCorrectly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("PATCH", "/patch-content.txt", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("204"));
    }

    @Test
    public void findsFourOhFourCorrectly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/404", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("404 Not Found"));
    }

    @Test
    public void findsFile1Correctly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("POST", "/file1", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("405"));
    }

    @Test
    public void findsTextFileCorrectly() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("POST", "/text-file.txt", "");
        assertThat(TestUtil.makeString(handler.handle(fakeRequest).asByteArray()), containsString("405"));
    }
}
