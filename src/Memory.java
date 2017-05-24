import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Memory {
    private String data;

    public void saveData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public String readFile(String path) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(path));
        return new String(fileBytes, "UTF-8");
    }

    public void removeData() {
        this.data = null;
    }

    public boolean hasData() {
        return this.data != null;
    }
}
