package br.com.agibank.service;

import br.com.agibank.parsers.sales.SalesParser;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReaderServiceTest {

    @Test
    public void ShouldFileAddedThrowsException() {

        String message = "Invalid format, only allowed .dat file extension";
        FileReaderService fileReaderService = new FileReaderService(new SalesParser());

        try {
            fileReaderService.validateExtension("arquivo.json");
        } catch (Exception e) {
            assertTrue(" Message should be " + message, e.getMessage().equals(message));
        }
    }

    @Test
    public void ShouldFileAddedSucceed() throws Exception {

        FileReaderService fileReaderService = new FileReaderService(new SalesParser());

        fileReaderService.validateExtension("arquivo.dat");

    }
}