import java.io.BufferedReader;
import java.io.IOException;

public interface HTTPServerManager {
    BufferedReader getInputStream();
    void setUp(int port) throws IOException;
    void acceptRequests() throws IOException;
    void output(String message) throws IOException;
}
