package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Sale;
import br.com.agibank.model.sales.SaleItem;
import br.com.agibank.model.sales.Salesman;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SaleParserTest {

    @Test
    public void ShoulParseLineSucceed() throws Exception {

        String line = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";
        Sale sale = SaleParser.parse(line);

        assertTrue(sale.getId().equals(8));
        assertTrue(sale.getSalesmanName().equals("Renato"));
        assertTrue(sale.getItens().size() == 3);

        SaleItem item = sale.getItens().get(0);
        assertTrue(item.getId().equals(1));
        assertTrue(item.getQuantity().doubleValue() == 34);
        assertTrue(item.getPrice().doubleValue() == 10);

        item = sale.getItens().get(1);
        assertTrue(item.getId().equals(2));
        assertTrue(item.getQuantity().doubleValue() == 33);
        assertTrue(item.getPrice().doubleValue() == 1.50);

        item = sale.getItens().get(2);
        assertTrue(item.getId().equals(3));
        assertTrue(item.getQuantity().doubleValue() == 40);
        assertTrue(item.getPrice().doubleValue() == 0.10);
    }

    @Test
    public void ShoulParseLineThrowsExceptionType() {

        String message = "The line must start with " + Constants.SALETYPE;
        String line = "001ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";
        try {
            Sale sale = SaleParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }

    }

    @Test
    public void ShoulParseLineThrowsNumberFormatExceptionForInteger() {

        try {
            String line = "003çACSç[1-34-10,2-33-1.50,3-40-0.10]çRenato";
            Sale sale = SaleParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }

    @Test
    public void ShoulParseLineThrowsNumberFormatExceptionForDecimal() {

        try {
            String line = "003ç1ç[1-34-10,2-33-CXS,3-40-0.10]çRenato";
            Sale sale = SaleParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(NumberFormatException.class));
        }
    }
}