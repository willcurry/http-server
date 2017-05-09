import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RequestParserTests {
    @Test
    public void parsesGETRequestCorrectly() throws RequestParser.InvalidRequest {
        RequestParser requestParser = new RequestParser();
        assertThat(requestParser.parse("GET / HTTP/1.1").get("type"), is("GET"));
    }
}
