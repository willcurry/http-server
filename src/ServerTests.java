import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        assertThat(out.toString(), is("HTTP/1.1 200 OK\n"));
    }

    @Test
    public void responseIsStatus200WhenPOST() throws IOException, RequestParser.InvalidRequest {
        before("POST / HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 200 OK\n"));
    }

    @Test
    public void responseIsStatus200WhenPUT() throws IOException, RequestParser.InvalidRequest {
        before("POST / HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 200 OK\n"));
    }

    @Test
    public void responseIsStatus404WhenUnknownURI() throws IOException, RequestParser.InvalidRequest {
        before("GET /foobar HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 404 Not Found\n"));
    }

    @Test
    public void responseIsStatus404WhenHeadRequestOnUnknownURI() throws IOException, RequestParser.InvalidRequest {
        before("HEAD /foobar HTTP/1.1");
        server.run();
        assertThat(out.toString(), is("HTTP/1.1 404 Not Found\n"));
    }
}
