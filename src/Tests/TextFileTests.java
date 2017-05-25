package Tests;

import Routes.TextFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TextFileTests {
    private TextFile textFile;
    private FakeRequest fakeRequest;

    private void setUpFakeRequest(String verb, String uri, String body) {
        fakeRequest = new FakeRequest(verb, uri, "HTTP/1.1", body);
    }

    @Before
    public void before() {
        textFile = new TextFile();
    }

    @Test
    public void getReturns200Response() throws IOException {
        setUpFakeRequest("GET", "/file1", "");
        assertThat(TestUtil.makeString(textFile.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void postReturns405Response() throws IOException {
        setUpFakeRequest("POST", "/file1", "");
        assertThat(TestUtil.makeString(textFile.handlePOST(fakeRequest).asByteArray()), is("HTTP/1.1 405 Method Not Allowed"));
    }
}
