package Server;

import java.io.BufferedReader;
import java.io.IOException;

public interface HTTPServerManager {
    void setUp(int port) throws IOException;
    void sendResponse() throws IOException;
}
