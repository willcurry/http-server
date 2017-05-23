import java.util.Map;

public interface Route {
    Response getResponse();
    Boolean appliesTo(String uri);
    Route withData(Map<String, String> data);
}
