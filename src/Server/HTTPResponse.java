package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HTTPResponse {
    private String version;
    private String statusCode;
    private String header;
    private byte[] content;
    private String contentType;

    public HTTPResponse() {
        contentType = "text/plain";
    }

    public void setHTTPVersion(String version) {
        this.version = version;
    }

    public void setStatusCode(Integer number, String message) {
        this.statusCode = number + " " + message;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] asByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String startLine = this.version + " " + this.statusCode;
        try {
            byteArrayOutputStream.write(startLine.getBytes());
            byteArrayOutputStream.write(header().getBytes());
            byteArrayOutputStream.write(content());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] content() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (content != null) {
            String header = "\nContent-Type: " + contentType + "\nContent-Length: " + content.length + "\n\n";
            byteArrayOutputStream.write(header.getBytes());
            byteArrayOutputStream.write(content);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private String header() {
        return (header == null) ? "" : "\n" + header + "\n";
    }
}
