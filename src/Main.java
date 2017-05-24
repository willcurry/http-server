import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server(5000, new ExitListener(), new ServerManager());
        server.run();
    }
}
