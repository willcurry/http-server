package Tests;

import Server.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class ServerManagerMock implements HTTPServerManager {
    private final BufferedReader input;
    private final OutputStream output;
    private final Handler handler;

    public ServerManagerMock(BufferedReader input, OutputStream output) {
        this.input = input;
        this.output = output;
        this.handler = new Handler();
    }

    @Override
    public void setUp(int port) throws IOException {

    }

    @Override
    public void sendResponse() throws IOException {
        output.write(getResponse(input).asByteArray());
        output.flush();
    }

    private Response getResponse(BufferedReader input) throws IOException {
        Request request = new Request(input);
        Logger.log(request);
        return handler.handle(request);
    }
}
