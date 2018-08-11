package br.com.agibank.Parsers;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SalesParserTest {

    @Test
    public void ShouldParseFileThrowsException() {
        String message = "Data type of this line is not valid";
        SalesParser parser = new SalesParser();
        try {
            parser.parseFile(Arrays.asList("004ç1234567891234çDiegoç50000"));
        } catch (Exception e) {
            assertTrue("Message should be" + message, e.getMessage().equals(message));
        }
    }
}