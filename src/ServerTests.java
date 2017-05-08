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

    @Before
    public void Before() {
        input = new BufferedReader(new StringReader("hello"));
        out = new StringWriter();
        output = new PrintWriter(out, true);
        server = new Server(input, output);
    }

    @Test
    public void ResponseIsStatus200WhenSimpleGET() throws IOException {
        server.run();
        assertThat(out.toString().contains("hello"), is(true));
    }
}
