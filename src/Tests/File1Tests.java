package Tests;

import Routes.File1;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class File1Tests {
    private File1 file1;
    private FakeRequest fakeRequest;

    private void setUpFakeRequest(String verb, String uri, String body) {
        fakeRequest = new FakeRequest(verb, uri, "HTTP/1.1", body);
    }

    @Before
    public void before() {
        file1 = new File1();
    }

    @Test
    public void getReturns200Response() throws IOException {
        setUpFakeRequest("GET", "/file1", "");
        assertThat(TestUtil.makeString(file1.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void postReturns405Response() throws IOException {
        setUpFakeRequest("POST", "/file1", "");
        assertThat(TestUtil.makeString(file1.handlePOST(fakeRequest).asByteArray()), is("HTTP/1.1 405 Method Not Allowed"));
    }
}
