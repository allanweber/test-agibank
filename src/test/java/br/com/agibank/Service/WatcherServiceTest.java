package br.com.agibank.Service;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

public class WatcherServiceTest {

    @Test
    public void ShouldNotRegisterListenerNull() {

        String message = "O parâmetro 'fileListener' não pode ser nulo";
        WatcherService watcherService = new WatcherService();

        try {
            watcherService.registerListener(null);
        } catch (Exception ex) {
            assertTrue("Mensagem deveria ser 'O parâmetro 'fileListener' não pode ser nulo'", ex.getMessage().equals(message));
        }

    }

    @Test
    public void ShouldRegisterListener() {

        String message = "O parâmetro 'fileListener' não pode ser nulo";
        WatcherService watcherService = new WatcherService();

        watcherService.registerListener(new FileParser());

        assertTrue("Deveria ter um listener", watcherService.getListeners().size() == 1);
    }

    @Test
    public void ShouldStartThrowsExceptionOfNullPath() {
        String message = "O parâmetro 'pathToWatch' não pode ser nulo";
        WatcherService watcherService = new WatcherService();

        try {
            watcherService.start(null);
        } catch (Exception ex) {
            assertTrue("Mensagem deveria ser 'O parâmetro 'pathToWatch' não pode ser nulo'", ex.getMessage().equals(message));
        }
    }

    @Test(expected = NoSuchFileException.class)
    public void ShouldStartThrowsExceptionOfPathNotFounded() throws IOException, InterruptedException {
        WatcherService watcherService = new WatcherService();

        watcherService.start("C:\\xyzwertyy");
    }
}