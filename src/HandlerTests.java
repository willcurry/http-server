import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HandlerTests {
    Map<String, String> fakeRequest;

    private void setUpFakeRequest(String uri) {
        fakeRequest = new HashMap<>();
        fakeRequest.put("uri", uri);
    }

    @Test
    public void returnsDefaultPage() {
        Handler handler = new Handler();
        setUpFakeRequest("/");
        assertTrue(handler.handle(fakeRequest) instanceof DefaultPage);
    }

    @Test
    public void returnsFormRequest() {
        Handler handler = new Handler();
        setUpFakeRequest("/form");
        assertTrue(handler.handle(fakeRequest) instanceof Form);
    }

    @Test
    public void returnsMethodOptionsRequest() {
        Handler handler = new Handler();
        setUpFakeRequest("/method_options");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions);
    }

    @Test
    public void returnsMethodOptions2Request() {
        Handler handler = new Handler();
        setUpFakeRequest("/method_options2");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions2);
    }

    @Test
    public void returnsRedirectRequest() {
        Handler handler = new Handler();
        setUpFakeRequest("/redirect");
        assertTrue(handler.handle(fakeRequest) instanceof Redirect);
    }

    @Test
    public void returnsFourOhFourRequest() {
        Handler handler = new Handler();
        setUpFakeRequest("/404");
        assertTrue(handler.handle(fakeRequest) instanceof FourOhFour);
    }
}
