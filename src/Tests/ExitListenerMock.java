package Tests;

import Server.Listener;

import java.io.BufferedReader;
import java.io.IOException;

public class ExitListenerMock implements Listener {
    private final BufferedReader input;

    public ExitListenerMock(BufferedReader input) {
        this.input = input;
    }

    @Override
    public boolean hasBeenTriggered() {
        try {
            return input.read() == -1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
