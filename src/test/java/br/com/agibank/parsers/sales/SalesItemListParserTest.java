package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Sale;
import br.com.agibank.model.sales.SaleItem;
import br.com.agibank.parsers.exceptions.InvalidFileDataSizeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SalesItemListParserTest {

    @Test
    public void ShoulParseLineSucceed() throws Exception {

        String line = "1-34-10,2-33-1.50,3-40-0.10";
        List<SaleItem> itens = SalesItemListParser.parse(line);

        assertTrue(itens.size() == 3);

        SaleItem item = itens.get(0);
        assertTrue(item.getId().equals(1));
        assertTrue(item.getQuantity().doubleValue() == 34);
        assertTrue(item.getPrice().doubleValue() == 10);

        item = itens.get(1);
        assertTrue(item.getId().equals(2));
        assertTrue(item.getQuantity().doubleValue() == 33);
        assertTrue(item.getPrice().doubleValue() == 1.50);

        item = itens.get(2);
        assertTrue(item.getId().equals(3));
        assertTrue(item.getQuantity().doubleValue() == 40);
        assertTrue(item.getPrice().doubleValue() == 0.10);
    }

    @Test
    public void ShoulParseLineSucceedWithBrackets() throws Exception {

        String line = "[1-34-10,2-33-1.50,3-40-0.10]";
        List<SaleItem> itens = SalesItemListParser.parse(line);

        assertTrue(itens.size() == 3);

        SaleItem item = itens.get(0);
        assertTrue(item.getId().equals(1));
        assertTrue(item.getQuantity().doubleValue() == 34);
        assertTrue(item.getPrice().doubleValue() == 10);
    }

    @Test
    public void ShoulParseLineThrowsNumberFormatExceptionForInteger() {

        try {
            String line = "1-34-10,2-33-1.50,A-40-0.10";
            List<SaleItem> itens = SalesItemListParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }

    @Test
    public void ShoulParseLineThrowsNumberFormatExceptionForDecimal() {

        try {
            String line = "1-34-10,2-33-1.50,3-40-ASD";
            List<SaleItem> itens = SalesItemListParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }

    @Test
    public void ShoulParseLineThrowsInvalidFileDataSizeException() {

        try {
            String line = "1-34-10,2-33-1.50,3-40-123-4";
            List<SaleItem> itens = SalesItemListParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(InvalidFileDataSizeException.class));
        }
    }
}