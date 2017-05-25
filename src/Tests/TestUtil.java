package Tests;

import java.io.UnsupportedEncodingException;

public class TestUtil {
    public static String makeString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, "UTF-8");
    }

    public static FakeRequest createFakeRequest(String verb, String uri, String body) {
        return new FakeRequest(verb, uri, "HTTP/1.1", body);
    }
}
