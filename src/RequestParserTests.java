import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RequestParserTests {
    @Test
    public void knowsTheMethodAfterParsing() throws RequestParser.InvalidRequest {
        RequestParser requestParser = new RequestParser();
        assertThat(requestParser.parse("GET / HTTP/1.1").get("verb"), is("GET"));
    }

    @Test
    public void knowsTheVersionAfterParsing() throws RequestParser.InvalidRequest {
        RequestParser requestParser = new RequestParser();
        assertThat(requestParser.parse("GET / HTTP/1.1").get("version"), is("HTTP/1.1"));
    }

    @Test
    public void knowsTheURIAfterParsing() throws RequestParser.InvalidRequest {
        RequestParser requestParser = new RequestParser();
        assertThat(requestParser.parse("GET / HTTP/1.1").get("uri"), is("/"));
    }

    @Test
    public void knowsTheBodyOfARequest() throws RequestParser.InvalidRequest {
        RequestParser requestParser = new RequestParser();
        assertThat(requestParser.parse("POST /form HTTP/1.1\n\ndata=fatcat").get("body"), is("data=fatcat"));
    }
}
