package Tests;

import Server.Request;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
                "Content-Length: 8\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\r\n\r\n" +
                "body\nlol");
        before();
        assertThat(request.getBody(), is("body\nlol"));
    }

    @Test
    public void knowsBaseURIOfQueryString() throws IOException {
        setFakeRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate");
        before();
        assertThat(request.getURI(), is("/parameters"));
    }

    @Test
    public void decodesURIs() throws IOException {
        setFakeRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate");
        before();
        String[] expected = new String[2];
        expected[0] = "variable_1 = Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";
        expected[1] = "variable_2 = stuff";
        assertArrayEquals(request.getURIParameters(), expected);
    }
}
