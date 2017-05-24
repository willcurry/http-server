import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Memory {
    private final String directory;
    private String data;

    public Memory(String directory) {
        this.directory = directory;
    }

    public void saveData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public String readFile(String path) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(directory + path));
        return new String(fileBytes, "UTF-8");
    }

    public void removeData() {
        this.data = null;
    }

    public boolean fileHasData(String path) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(directory + path));
        return fileBytes.length > 1;
    }

    public boolean hasData() {
        return this.data != null;
    }

    public void writeToFile(String path, String text) throws IOException {
        Files.write(Paths.get(directory+ path), text.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
