package Server;

import java.io.IOException;

public interface HTTPServerManager extends Runnable {
    void setUp(int port, Router router) throws IOException;
    void sendResponse() throws IOException;
}
