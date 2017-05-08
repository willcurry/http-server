import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RequestParserTests {
    @Test
    public void parsesCorrectly() {
        RequestParser requestParser = new RequestParser();
        assertThat(requestParser.parse("GET / HTTP/1.1").get("type"), is("GET"));
    }
}
