package br.com.agibank.service;

import br.com.agibank.parsers.sales.SalesFileParser;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReaderServiceTest {

    @Test
    public void ShouldFileAddedThrowsException() {

        String message = "Invalid format, only allowed .dat file extension";
        FileReaderService fileReaderService = new FileReaderService(new SalesFileParser());

        try {
            fileReaderService.validateExtension("arquivo.json");
        } catch (Exception e) {
            assertTrue(" Message should be " + message, e.getMessage().equals(message));
        }
    }

    @Test
    public void ShouldFileAddedSucceed() throws Exception {

        FileReaderService fileReaderService = new FileReaderService(new SalesFileParser());

        fileReaderService.validateExtension("arquivo.dat");

    }
}