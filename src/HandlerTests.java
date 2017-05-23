import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class HandlerTests {
    FakeRequest fakeRequest;
    private Handler handler;

    private void setUpFakeRequest(String uri) {
        handler = new Handler();
        fakeRequest = new FakeRequest(uri);
    }

    @Test
    public void returnsDefaultPage() throws IOException {
        setUpFakeRequest("/");
        assertTrue(handler.handle(fakeRequest) instanceof DefaultPage);
    }

    @Test
    public void returnsFormRequest() throws IOException {
        setUpFakeRequest("/form");
        assertTrue(handler.handle(fakeRequest) instanceof Form);
    }

    @Test
    public void returnsMethodOptionsRequest() throws IOException {
        setUpFakeRequest("/method_options");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions);
    }

    @Test
    public void returnsMethodOptions2Request() throws IOException {
        setUpFakeRequest("/method_options2");
        assertTrue(handler.handle(fakeRequest) instanceof MethodOptions2);
    }

    @Test
    public void returnsRedirectRequest() throws IOException {
        setUpFakeRequest("/redirect");
        assertTrue(handler.handle(fakeRequest) instanceof Redirect);
    }

    @Test
    public void returnsFourOhFourRequest() throws IOException {
        setUpFakeRequest("/404");
        assertTrue(handler.handle(fakeRequest) instanceof FourOhFour);
    }
}
