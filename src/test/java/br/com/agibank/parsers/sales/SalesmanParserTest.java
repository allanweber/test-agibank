package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Salesman;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalesmanParserTest {

    @Test
    public void ShoulParseLineSucessed() throws Exception {

        String line = "001ç3245678865434çRenatoç40000.99";
        Salesman salesman = SalesmanParser.parse(line);

        assertTrue(salesman.getCpf().equals("3245678865434"));
        assertTrue(salesman.getName().equals("Renato"));
        assertTrue(salesman.getSalary().doubleValue() == 40000.99);
    }

    @Test
    public void ShoulParseLineThriwsException() {

        String message = "The line must start with " + Constants.SALESMATYPE;
        String line = "004ç3245678865434çRenatoç40000.99";
        try {
            Salesman salesman = SalesmanParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }

    }
}