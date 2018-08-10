package br.com.agibank.app;

import java.io.IOException;
import java.nio.file.*;

public class Executer {

    public static void main(String[] args) {

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get("in");

            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                }
                key.reset();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
