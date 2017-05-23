import org.junit.Test;


import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class RequestTests {
    private Request request;
    private Reader input;
    private String fakeRequest;

    private void setFakeRequest(String fakeRequest) {
        this.fakeRequest = fakeRequest;
    }

    public void before() throws IOException {
        input = new StringReader(fakeRequest);
        request = new Request(input);
    }

    @Test
    public void canGetVerb() throws IOException {
        setFakeRequest("GET / HTTP/1.1\n" +
                           "Host: localhost:5000\n" +
                           "Connection: Keep-Alive\n" +
                           "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                           "Accept-Encoding: gzip,deflate");
        before();
        assertEquals(request.getVerb(), "GET");
    }

    @Test
    public void canGetURI() throws IOException {
        setFakeRequest("GET / HTTP/1.1\n" +
                           "Host: localhost:5000\n" +
                           "Connection: Keep-Alive\n" +
                           "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                           "Accept-Encoding: gzip,deflate");
        before();
        assertEquals(request.getURI(), "/");
    }

    @Test
    public void canGetHTTPVersion() throws IOException {
        setFakeRequest("GET / HTTP/1.1\n" +
                           "Host: localhost:5000\n" +
                           "Connection: Keep-Alive\n" +
                           "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                           "Accept-Encoding: gzip,deflate");
        before();
        assertEquals(request.getHTTPVersion(), "HTTP/1.1");
    }
}
