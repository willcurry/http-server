import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class HandlerTests {
    private FakeRequest fakeRequest;
    private Handler handler;

    private void setUpFakeRequest(String verb, String uri, String body) {
        handler = new Handler();
        fakeRequest = new FakeRequest(verb, uri, "HTTP/1.1", body);
    }

    @Test
    public void returnsDefaultPage() throws IOException {
        setUpFakeRequest("GET", "/", "");
        assertTrue(handler.handle(fakeRequest) instanceof DefaultPage);
    }

    @Test
    public void returnsFormRequest() throws IOException {
        setUpFakeRequest("GET", "/form", "");
        assertTrue(handler.handle(fakeRequest) instanceof Form);
    }

    @Test
    public void returnsMethodOptionsRequest() throws IOException {
        setUpFakeRequest("GET", "/method_options", "");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions);
    }

    @Test
    public void returnsMethodOptions2Request() throws IOException {
        setUpFakeRequest("GET", "/method_options2", "");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions2);
    }

    @Test
    public void returnsRedirectRequest() throws IOException {
        setUpFakeRequest("GET", "/redirect", "");
        assertTrue(handler.handle(fakeRequest) instanceof Redirect);
    }

    @Test
    public void returnsFourOhFourRequest() throws IOException {
        setUpFakeRequest("GET", "/404", "");
        assertTrue(handler.handle(fakeRequest) instanceof FourOhFour);
    }
}
