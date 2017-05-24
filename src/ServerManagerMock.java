import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ServerManagerMock implements HTTPServerManager {
    private final BufferedReader input;
    private final OutputStream output;

    public ServerManagerMock(BufferedReader input, OutputStream output) {
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
    public void output(byte[] message) throws IOException {
        output.write(message);
        output.flush();
    }
}
