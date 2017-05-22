import java.util.Map;

public interface Request {
    Response getResponse();
    Boolean appliesTo(String uri);
    Request withData(Map<String, String> data);
}
