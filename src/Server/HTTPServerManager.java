package Server;

import java.io.IOException;

public interface HTTPServerManager {
    void setUp(int port, Router router) throws IOException;
    void sendResponse() throws IOException;
}
