package br.com.agibank.parsers;

import br.com.agibank.model.sales.SalesFile;
import br.com.agibank.model.sales.Sale;
import br.com.agibank.parsers.sales.SalesFileParser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SalesFileParserTest {

    @Test
    public void ShouldParseFileThrowsException() {
        String message = "Data type of this line is not valid";
        SalesFileParser parser = new SalesFileParser("");
        try {
            parser.parseFile(Arrays.asList("004ç1234567891234çDiegoç50000"));
        } catch (Exception e) {
            assertTrue("Message should be" + message, e.getMessage().equals(message));
        }
    }

    @Test
    public void ShouldParseFileSucceed() {

        List<String> lines =
                Arrays.asList(
                        "001ç1234567891234çDiegoç50000",
                        "001ç3245678865434çRenatoç40000.99",
                        "002ç2345675434544345çJose da SilvaçRural",
                        "002ç2345675433444345çEduardo PereiraçRural",
                        "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego",
                        "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato");

        SalesFileParser parser = new SalesFileParser("");
        parser.parseFile(lines);

        SalesFile file  = parser.getFullSaleFile();

        assertTrue(file.getSalesmen().size() == 2);
        assertTrue(file.getCustomers().size() == 2);
        assertTrue(file.getSales().size() == 2);

        Sale sale = file.getSales().get(0);
        assertTrue(sale.getItens().size() == 3);
    }
}