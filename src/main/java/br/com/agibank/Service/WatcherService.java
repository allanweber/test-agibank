package br.com.agibank.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WatcherService {

    private List<IFileEventListener> fileEventListener;

    public WatcherService() {
        fileEventListener = new ArrayList<>();
    }

    public void registerListener(IFileEventListener fileListener) {
        Objects.requireNonNull(fileListener, "O parâmetro 'fileListener' não pode ser nulo");
        this.fileEventListener.add(fileListener);
    }

    public List<IFileEventListener> getListeners() {
        return this.fileEventListener;
    }

    public void start(String pathToWatch) throws IOException, InterruptedException {

        Objects.requireNonNull(pathToWatch, "O parâmetro 'pathToWatch' não pode ser nulo");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(pathToWatch);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                for (IFileEventListener listener : fileEventListener) {

                    new Thread(() -> {
                        listener.FileAdded(pathToWatch.concat("\\").concat(event.context().toString()));
                    }).start();

                }
            }
            key.reset();
        }

    }
}
