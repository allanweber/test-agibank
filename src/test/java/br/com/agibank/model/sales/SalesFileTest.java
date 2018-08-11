package br.com.agibank.model.sales;

import br.com.agibank.parsers.exceptions.FileDataException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SalesFileTest {

    private SalesFile salesFile;

    @Before
    public void setup() throws FileDataException {
        salesFile = new SalesFile();

        salesFile.addSalesman(
                Salesman.builder()
                        .cpf("2345")
                        .name("Vendedor 1")
                        .salary(BigDecimal.valueOf(1234.56))
                        .build()
        );

        salesFile.addCustomer(
                Customer.builder()
                        .businessArea("IT")
                        .cpnj("12345678909876")
                        .name("Cliente")
                        .build()
        );

        salesFile.addSale(
                Sale.builder()
                        .id(1)
                        .salesman(Salesman.builder()
                                .cpf("2345")
                                .name("Vendedor 1")
                                .salary(BigDecimal.valueOf(1234.56))
                                .build()
                        )
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(2.55))
                                                .quantity(BigDecimal.valueOf(3))
                                                .build()
                                )
                        )
                        .build()
        );
    }

    @Test
    public void getSalesmen() {

        assertTrue(salesFile.getSalesmen().size() == 1);
        Salesman salesman = salesFile.getSalesmen().get(0);
        assertTrue(salesman.getCpf().equals("2345"));
    }

    @Test
    public void addSalesman() {
        assertTrue(salesFile.getSalesmen().size() == 1);

        salesFile.addSalesman(Salesman.builder().name("Outro").build());
        assertTrue(salesFile.getSalesmen().size() == 2);
        Salesman salesman = salesFile.getSalesmen().get(1);
        assertTrue(salesman.getCpf() == null);
    }

    @Test
    public void getCustomers() {

        assertTrue(salesFile.getCustomers().size() == 1);
        Customer customer = salesFile.getCustomers().get(0);
        assertTrue(customer.getCpnj().equals("12345678909876"));

    }

    @Test
    public void addCustomer() {

        assertTrue(salesFile.getCustomers().size() == 1);

        salesFile.addCustomer(Customer.builder().name("Mais um").build());
        assertTrue(salesFile.getCustomers().size() == 2);
        Customer customer = salesFile.getCustomers().get(1);
        assertTrue(customer.getCpnj() == null);
    }

    @Test
    public void getSales() {

        assertTrue(salesFile.getSales().size() == 1);
        Sale sale = salesFile.getSales().get(0);
        assertTrue(sale.getItens().size() == 1);

    }

    @Test
    public void addSale() throws FileDataException {
        assertTrue(salesFile.getSales().size() == 1);

        salesFile.addSale(
                Sale.builder()
                        .id(2)
                        .salesman(Salesman.builder()
                                .cpf("2345")
                                .name("Vendedor 1")
                                .salary(BigDecimal.valueOf(1234.56))
                                .build()
                        )
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(5))
                                                .quantity(BigDecimal.valueOf(6.78))
                                                .build()
                                )
                        )
                        .build()
        );

        assertTrue(salesFile.getSales().size() == 2);
        Sale sale = salesFile.getSales().get(1);
        assertTrue(sale.getItens().size() == 1);
        assertTrue(sale.getId() == 2);
    }
}