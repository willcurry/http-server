import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ServerTests {
    Server server;
    BufferedReader input;
    StringWriter out;
    PrintWriter output;
    ExitListenerMock exitListener;

    public void before(String request) throws IOException {
        input = new BufferedReader(new StringReader(request));
        out = new StringWriter();
        output = new PrintWriter(out, true);
        exitListener = new ExitListenerMock(input);
        server = new Server(5000, exitListener, new ServerManagerMock(input, output));
        server.run();
    }

    @Test
    public void responseIsStatus200WhenGET() throws IOException {
        before("GET / HTTP/1.1");
        assertThat(out.toString(), containsString("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus200WhenPOST() throws IOException {
        before("POST / HTTP/1.1");
        assertThat(out.toString(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus200WhenPUT() throws IOException {
        before("POST / HTTP/1.1");
        assertThat(out.toString(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus404WhenUnknownURI() throws IOException {
        before("GET /foobar HTTP/1.1");
        assertThat(out.toString(), is("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void responseIsStatus404WhenHeadRequestOnUnknownURI() throws IOException {
        before("HEAD /foobar HTTP/1.1");
        assertThat(out.toString(), is("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void whenURIIsMethodOptionsTheHeaderAllowsCorrectMethods() throws IOException {
        before("GET /method_options HTTP/1.1");
        assertThat(out.toString(), containsString("ALLOW: GET,HEAD,POST,OPTIONS,PUT"));
    }

    @Test
    public void whenURIIsMethodOptions2TheHeaderAllowsCorrectMethods() throws IOException {
        before("GET /method_options2 HTTP/1.1");
        assertThat(out.toString(), containsString("ALLOW: GET,OPTIONS"));
    }

    @Test
    public void responseIsStatus302WhenURIIsRedirect() throws IOException {
        before("GET /redirect HTTP/1.1");
        assertThat(out.toString().contains("HTTP/1.1 302 Found"), is(true));
    }

    @Test
    public void locationIsSetToHomeWhenURIIsRedirect() throws IOException {
        before("GET /redirect HTTP/1.1");
        assertThat(out.toString().contains("Location: /"), is(true));
    }
}
