public interface Route {
    Response getResponse();
    Boolean appliesTo(String uri);
    Route withData(HTTPRequest request);
}
