import java.io.UnsupportedEncodingException;

public class Util {
    public static String makeString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, "UTF-8");
    }
}
