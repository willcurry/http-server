package Server;

import java.io.IOException;
import java.util.ArrayList;

public interface HTTPRequest {
    String getVerb() throws IOException;
    String getURI() throws IOException;
    String getHTTPVersion() throws IOException;
    ArrayList<String> getHeaders() throws IOException;
    String getBody() throws IOException;
}
