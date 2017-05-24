import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerManagerMock implements HTTPServerManager {
    private final BufferedReader input;
    private final PrintWriter output;

    public ServerManagerMock(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public BufferedReader getInputStream() {
        return this.input;
    }

    @Override
    public void setUp(int port) throws IOException {

    }

    @Override
    public void acceptRequests() throws IOException {

    }

    @Override
    public void output(String message) throws IOException {
        output.print(message);
        output.flush();
    }
}
