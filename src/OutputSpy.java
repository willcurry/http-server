import java.io.*;

public class OutputSpy extends OutputStream {
    public ByteArrayOutputStream byteStream;

    public OutputSpy() {
        byteStream = new ByteArrayOutputStream();
    }

    @Override
    public void write(int b) throws IOException {
        byteStream.write(b);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        byteStream.write(bytes);
    }

    public byte[] getAllBytes() {
        return byteStream.toByteArray();
    }
}
