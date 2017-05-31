package Server;

import java.io.IOException;
import java.util.ArrayList;

public class Logger {
    public static final ArrayList<String> log = new ArrayList<>();

    public static void log(HTTPRequest request) throws IOException {
        log.add(request.getVerb() + " " + request.getURI() + " " + request.getHTTPVersion());
    }

    public static ArrayList<String> getLogs() {
        return log;
    }
}
