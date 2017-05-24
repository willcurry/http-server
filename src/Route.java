import java.io.IOException;

public interface Route {
    Response getResponse() throws IOException;
    Boolean appliesTo(String uri);
    Route withData(HTTPRequest request);
}
