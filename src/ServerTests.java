import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ServerTests {
    Server server;
    BufferedReader input;
    StringWriter out;
    PrintWriter output;

    public void before(String request) {
        input = new BufferedReader(new StringReader(request));
        out = new StringWriter();
        output = new PrintWriter(out, true);
        server = new Server(input, output);
    }

    @Test
    public void responseIsStatus200WhenGET() throws IOException, RequestParser.InvalidRequest {
        before("GET / HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus200WhenPOST() throws IOException, RequestParser.InvalidRequest {
        before("POST / HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus200WhenPUT() throws IOException, RequestParser.InvalidRequest {
        before("POST / HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus404WhenUnknownURI() throws IOException, RequestParser.InvalidRequest {
        before("GET /foobar HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void responseIsStatus404WhenHeadRequestOnUnknownURI() throws IOException, RequestParser.InvalidRequest {
        before("HEAD /foobar HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void whenURIIsMethodOptionsTheHeaderAllowsCorrectMethods() throws IOException, RequestParser.InvalidRequest {
        before("GET /method_options HTTP/1.1");
        server.run();
        assertThat(out.toString(), containsString("ALLOW: GET,HEAD,POST,OPTIONS,PUT"));
    }

    @Test
    public void whenURIIsMethodOptions2TheHeaderAllowsCorrectMethods() throws IOException, RequestParser.InvalidRequest {
        before("GET /method_options2 HTTP/1.1");
        server.run();
        assertThat(out.toString(), containsString("ALLOW: GET,OPTIONS"));
    }

    @Test
    public void responseIsStatus302WhenURIIsRedirect() throws IOException, RequestParser.InvalidRequest {
        before("GET /redirect HTTP/1.1");
        server.run();
        assertThat(out.toString().contains("HTTP/1.1 302 Found"), is(true));
    }

    @Test
    public void locationIsSetToHomeWhenURIIsRedirect() throws IOException, RequestParser.InvalidRequest {
        before("GET /redirect HTTP/1.1");
        server.run();
        assertThat(out.toString().contains("Location: /"), is(true));
    }

    @Test
    public void whenDataIsPostedToFormTheResponseOfTheBodyContainsTheData() throws IOException, RequestParser.InvalidRequest {
        input = new BufferedReader(new StringReader("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat"));
        out = new StringWriter();
        output = new PrintWriter(out, true);
        server = new Server(input, output);
        server.run();
        server.input = new BufferedReader(new StringReader("GET /form HTTP/1.1"));
        server.run();
        assertThat(out.toString(), containsString("data=fatcat"));
    }
}
