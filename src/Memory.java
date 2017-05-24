import java.util.HashMap;

public class Memory {
    private final HashMap<String, String> data;

    public Memory() {
        this.data = new HashMap<>();
    }

    public void saveData(String uri, String data) {
        this.data.put(uri, data);
    }

    public String dataForURI(String uri) {
        return data.get(uri);
    }
}
