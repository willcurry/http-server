import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

    @Test
    public void canGetHeaders() throws IOException {
        setFakeRequest("GET / HTTP/1.1\n" +
                       "Host: localhost:5000\n" +
                       "Connection: Keep-Alive\n" +
                       "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                       "Accept-Encoding: gzip,deflate\n\n" +
                       "body");
        before();
        String[] expected = new String[]{"GET / HTTP/1.1", "Host: localhost:5000", "Connection: Keep-Alive", "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)", "Accept-Encoding: gzip,deflate"};
        assertArrayEquals(expected, (request.getHeaders().toArray()));
    }

    @Test
    public void canGetBody() throws IOException {
        setFakeRequest("GET / HTTP/1.1\n" +
                       "Content-Length: 4\n" +
                       "Host: localhost:5000\n" +
                       "Connection: Keep-Alive\n" +
                       "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                       "Accept-Encoding: gzip,deflate\r\n\r\n" +
                       "body");
        before();
        assertEquals("body", request.getBody());
    }

    @Test
    public void canGetBodyWithNewLines() throws IOException {
        setFakeRequest("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\r\n\r\n" +
                "body\nlol");
        before();
        assertThat(request.getBody(), is("body\nlol"));
    }
}
