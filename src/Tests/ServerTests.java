package Tests;

import Server.Server;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ServerTests {
    Server server;
    BufferedReader input;
    OutputSpy output;
    ExitListenerMock exitListener;

    public void before(String request) throws IOException {
        input = new BufferedReader(new StringReader(request));
        output = new OutputSpy();
        exitListener = new ExitListenerMock(input);
        server = new Server(5000, exitListener, new ServerManagerMock(input, output));
        server.run();
    }

    @Test
    public void responseIsStatus200WhenGET() throws IOException {
        before(" GET / HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus200WhenPOST() throws IOException {
        before(" POST /form HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus200WhenPUT() throws IOException {
        before(" PUT /form HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseIsStatus404WhenUnknownURI() throws IOException {
        before(" GET /foobar HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void responseIsStatus404WhenHeadRequestOnUnknownURI() throws IOException {
        before(" HEAD /foobar HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void whenURIIsMethodOptionsTheHeaderAllowsCorrectMethods() throws IOException {
        before(" OPTIONS /method_options HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("ALLOW: GET,HEAD,POST,OPTIONS,PUT"));
    }

    @Test
    public void whenURIIsMethodOptions2TheHeaderAllowsCorrectMethods() throws IOException {
        before(" OPTIONS /method_options2 HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("ALLOW: GET,OPTIONS"));
    }

    @Test
    public void responseIsStatus302WhenURIIsRedirect() throws IOException {
        before(" GET /redirect HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("HTTP/1.1 302 Found"));
    }

    @Test
    public void locationIsSetToHomeWhenURIIsRedirect() throws IOException {
        before(" GET /redirect HTTP/1.1");
        assertThat(TestUtil.makeString(output.getAllBytes()), containsString("Location: /"));
    }
}
