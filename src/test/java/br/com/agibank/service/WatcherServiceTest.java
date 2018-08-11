package br.com.agibank.service;

import br.com.agibank.parsers.sales.SalesFileParser;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

public class WatcherServiceTest {

    @Test
    public void ShouldNotRegisterListenerNull() {

        String message = "The parameter 'fileListener' mustn't be null";
        WatcherService watcherService = new WatcherService();

        try {
            watcherService.registerListener(null);
        } catch (Exception ex) {
            assertTrue("Message should be " + message, ex.getMessage().equals(message));
        }

    }

    @Test
    public void ShouldRegisterListener() {
        WatcherService watcherService = new WatcherService();

        FileReaderService reader = new FileReaderService(new SalesFileParser());
        watcherService.registerListener(reader);

        assertTrue("Should has one listener", watcherService.getListeners().size() == 1);
    }

    @Test
    public void ShouldStartThrowsExceptionOfNullPath() {
        String message = "The parameter 'pathToWatch' mustn't be null";
        WatcherService watcherService = new WatcherService();

        try {
            watcherService.start(null);
        } catch (Exception ex) {
            assertTrue("Message should be " + message, ex.getMessage().equals(message));
        }
    }

    @Test(expected = NoSuchFileException.class)
    public void ShouldStartThrowsExceptionOfPathNotFounded() throws IOException, InterruptedException {
        WatcherService watcherService = new WatcherService();

        watcherService.start("C:\\xyzwertyy");
    }
}