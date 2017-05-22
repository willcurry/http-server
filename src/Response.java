public class Response {
    private String version;
    private String statusCode;
    private String header;
    private String content;

    public void setHTTPVersion(String version) {
        this.version = version;
    }

    public void setStatusCode(Integer number, String message) {
        this.statusCode = number + " " + message;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return this.version + " " + this.statusCode + header() + content();
    }

    private String content() {
        return (content == null) ? "" : "\n" + content;
    }

    private String header() {
        return (header == null) ? "" : "\n" + header + "\n";
    }
}
