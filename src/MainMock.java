import java.io.*;

public class MainMock {
    public static void main(String[] fakeInputs, StringWriter out) throws IOException, RequestParser.InvalidRequest {
        for (String fakeInput : fakeInputs) {
            BufferedReader input = new BufferedReader(new StringReader(fakeInput));
            PrintWriter output = new PrintWriter(out, true);
            Server server = new Server(input, output);
            server.run();
        }
    }
}
