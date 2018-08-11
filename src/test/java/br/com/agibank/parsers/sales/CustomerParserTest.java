package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Customer;
import br.com.agibank.model.sales.Salesman;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerParserTest {

    @Test
    public void ShoulParseLineSucceed() throws Exception {

        String line = "002ç2345675434544345çJose da SilvaçRural";
        Customer customer = CustomerParser.parse(line);

        assertTrue(customer.getCpnj().equals("2345675434544345"));
        assertTrue(customer.getName().equals("Jose da Silva"));
        assertTrue(customer.getBusinessArea().equals("Rural"));
    }

    @Test
    public void ShoulParseLineThriwsException() {

        String message = "The line must start with " + Constants.CUSTOMERTYPE;
        String line = "001ç2345675434544345çJose da SilvaçRural";
        try {
            Customer customer = CustomerParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }

    }
}