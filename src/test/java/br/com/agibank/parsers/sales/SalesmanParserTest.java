package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.SaleItem;
import br.com.agibank.model.sales.Salesman;
import br.com.agibank.parsers.exceptions.InvalidFileDataSizeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SalesmanParserTest {

    @Test
    public void ShoulParseLineSucceed() throws Exception {

        String line = "001ç3245678865434çRenatoç40000.99";
        Salesman salesman = SalesmanParser.parse(line);

        assertTrue(salesman.getCpf().equals("3245678865434"));
        assertTrue(salesman.getName().equals("Renato"));
        assertTrue(salesman.getSalary().doubleValue() == 40000.99);
    }

    @Test
    public void ShoulParseLineThrowsException() {

        String message = "The line must start with " + Constants.SALESMANTYPE;
        String line = "002ç3245678865434çRenatoç40000.99";
        try {
            Salesman salesman = SalesmanParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }

    }

    @Test
    public void ShoulParseLineThrowsNumberFormatException() {

        String message = "The line must start with " + Constants.SALESMANTYPE;
        String line = "001ç3245678865434çRenatoçSALARY";
        try {
            Salesman salesman = SalesmanParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }

    @Test
    public void ShoulParseLineThrowsInvalidFileDataSizeException() {

        try {
            String line = "001ç3245678865434çRenatoç45000çOutro";
            Salesman salesman = SalesmanParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(InvalidFileDataSizeException.class));
        }
    }
}