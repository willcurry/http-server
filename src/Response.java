public class Response {
    private String version;
    private String statusCode;
    private String header;

    public void setHTTPVersion(String version) {
        this.version = version;
    }

    public void setStatusCode(Integer number, String message) {
        this.statusCode = number + " " + message;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    private String header() {
        return (header == null) ? "" : "\r\n" + header;
    }

    public String toString() {
        return this.version + " " + this.statusCode + header();
    }
}
