public interface Request {
    Response getResponse();
    Boolean appliesTo(String uri);
}
