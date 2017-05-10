import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RequestParserTests {
    @Test
    public void knowsTheMethodAfterParsing() throws RequestParser.InvalidRequest {
        RequestParser requestParser = new RequestParser();
        assertThat(requestParser.parse("GET / HTTP/1.1").get("type"), is("GET"));
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
}
