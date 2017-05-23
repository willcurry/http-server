import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class HandlerTests {
    Map<String, String> fakeRequest;
    private Handler handler;

    private void setUpFakeRequest(String uri) {
        handler = new Handler();
        fakeRequest = new HashMap<>();
        fakeRequest.put("uri", uri);
    }

    @Test
    public void returnsDefaultPage() {
        setUpFakeRequest("/");
        assertTrue(handler.handle(fakeRequest) instanceof DefaultPage);
    }

    @Test
    public void returnsFormRequest() {
        setUpFakeRequest("/form");
        assertTrue(handler.handle(fakeRequest) instanceof Form);
    }

    @Test
    public void returnsMethodOptionsRequest() {
        setUpFakeRequest("/method_options");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions);
    }

    @Test
    public void returnsMethodOptions2Request() {
        setUpFakeRequest("/method_options2");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions2);
    }

    @Test
    public void returnsRedirectRequest() {
        setUpFakeRequest("/redirect");
        assertTrue(handler.handle(fakeRequest) instanceof Redirect);
    }

    @Test
    public void returnsFourOhFourRequest() {
        setUpFakeRequest("/404");
        assertTrue(handler.handle(fakeRequest) instanceof FourOhFour);
    }
}
