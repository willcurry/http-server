package Routes;

import Server.Storage;

import java.io.IOException;
import java.nio.file.Path;

public class PublicFilesRoute extends BaseRoute {
    private final Storage storage;

    public PublicFilesRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) throws IOException {
        String formattedURI = uri.substring(1);
        for (Path path : storage.allFiles()) {
            if (path.equals(storage.getPath(formattedURI))) return true;
        }
        return false;
    }
}
