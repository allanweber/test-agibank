package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.SaleItem;
import br.com.agibank.model.sales.Salesman;
import br.com.agibank.parsers.exceptions.InvalidFileDataSizeException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleItemParserTest {

    @Test
    public void ShoulParseLineSucceed() throws Exception {

        String line = "1-34-10";
        SaleItem item = SaleItemParser.parse(line);

        assertTrue(item.getId().equals(1));
        assertTrue(item.getQuantity().doubleValue() == 34);
        assertTrue(item.getPrice().doubleValue() == 10);
    }

    @Test
    public void ShoulParseLineSucceedWithDecimals() throws Exception {

        String line = "2-33.56-1.50";
        SaleItem item = SaleItemParser.parse(line);

        assertTrue(item.getId().equals(2));
        assertTrue(item.getQuantity().doubleValue() == 33.56);
        assertTrue(item.getPrice().doubleValue() == 1.50);
    }

    @Test
    public void ShoulParseLineThrowsNumberFormatExceptionForId() {

        try {
            String line = "A-33.56-1.50";
            SaleItem item = SaleItemParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }

    @Test
    public void ShoulParseLineThrowsNumberFormatExceptionForQuantity() {

        try {
            String line = "2-ABC-1.50";
            SaleItem item = SaleItemParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }

    @Test
    public void ShoulParseLineThrowsNumberFormatExceptionForPrice() {

        try {
            String line = "2-23.45-XYZ";
            SaleItem item = SaleItemParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }

    @Test
    public void ShoulParseLineThrowsInvalidFileDataSizeException() {

        try {
            String line = "2-23.45-123-4";
            SaleItem item = SaleItemParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(InvalidFileDataSizeException.class));
        }
    }
}