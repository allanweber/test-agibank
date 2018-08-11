package br.com.agibank.Service;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReaderServiceTest {

    @Test
    public void ShouldFileAddedThrowsException() {

        String message = "Invalid format, only allowed .dat file extension";
        FileReaderService fileReaderService = new FileReaderService();

        try {
            fileReaderService.validateExtension("arquivo.json");
        } catch (Exception e) {
            assertTrue(" Message should be " + message, e.getMessage().equals(message));
        }
    }

    @Test
    public void ShouldFileAddedSucceed() throws Exception {

        FileReaderService fileReaderService = new FileReaderService();

        fileReaderService.validateExtension("arquivo.dat");

    }
}