package br.com.agibank.model.sales;

import br.com.agibank.parsers.exceptions.FileDataException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SalesFileLogicTest {

    @Test
    public void notShouldAddSaleWithoutSalesman(){
        SalesFile file = new SalesFile();
        Sale sale = Sale.builder()
                .id(1)
                .salesmanName("Vendedor")
                .itens(
                        Arrays.asList(
                                SaleItem.builder()
                                        .id(1)
                                        .price(BigDecimal.valueOf(2.55))
                                        .quantity(BigDecimal.valueOf(3))
                                        .build()
                        )
                )
                .build();

        Sale saleNoMan = Sale.builder()
                .id(1)
                .itens(
                        Arrays.asList(
                                SaleItem.builder()
                                        .id(1)
                                        .price(BigDecimal.valueOf(2.55))
                                        .quantity(BigDecimal.valueOf(3))
                                        .build()
                        )
                )
                .build();

        try {
            file.addSale(sale);
            assertTrue(file.getSales().size() == 1);
            file.addSale(saleNoMan);
        } catch (FileDataException e) {
            assertTrue(e.getClass().equals(FileDataException.class));
        }
    }

    @Test
    public void notShouldAddEqualSalesMan() {
        SalesFile file = new SalesFile();

        Salesman vendedor2 = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 2")
                .salary(BigDecimal.valueOf(1234.56))
                .build();

        file.addSalesman(vendedor2);

        assertTrue(file.getSalesmen().size() == 1);

        Salesman vendedor2New = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 2")
                .salary(BigDecimal.valueOf(1234.56))
                .build();

        file.addSalesman(vendedor2New);

        assertTrue(file.getSalesmen().size() == 1);

        Salesman vendedor3 = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 3")
                .salary(BigDecimal.valueOf(1234.56))
                .build();

        file.addSalesman(vendedor3);

        assertTrue(file.getSalesmen().size() == 2);
    }

    @Test
    public void shouldSetSalesmanToSale() throws FileDataException {
        SalesFile file = new SalesFile();
        file.addSale(
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

        file.addSale(
                Sale.builder()
                        .id(1)
                        .salesmanName("Vendedor 2")
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

        Salesman vendedor2 = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 2")
                .salary(BigDecimal.valueOf(1234.56))
                .build();

        file.addSalesman(vendedor2);

        Sale sale = file.getSales().get(0);
        assertTrue(sale.getSalesman().getName().equals("Vendedor 1"));

        sale = file.getSales().get(1);
        assertTrue(sale.getSalesman().getName().equals("Vendedor 2"));
    }

    @Test
    public void notShouldSetSalesmanToSale() throws FileDataException {
        SalesFile file = new SalesFile();
        file.addSale(
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

        file.addSale(
                Sale.builder()
                        .id(1)
                        .salesmanName("Vendedor 2")
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

        Salesman vendedor3 = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 3")
                .salary(BigDecimal.valueOf(1234.56))
                .build();

        file.addSalesman(vendedor3);

        Sale sale = file.getSales().get(0);
        assertTrue(sale.getSalesman().getName().equals("Vendedor 1"));

        sale = file.getSales().get(1);
        assertTrue(sale.getSalesman() == null);
    }

    @Test
    public void notShouldAddEqualCustomer(){
        SalesFile file = new SalesFile();

        Customer customer = Customer.builder().name("Cliente 1").businessArea("IT").cpnj("123456").build();
        file.addCustomer(customer);

        assertTrue(file.getCustomers().size() == 1);

        Customer customerEqual = Customer.builder().name("Cliente 1").businessArea("IT").cpnj("123456").build();
        file.addCustomer(customerEqual);
        assertTrue(file.getCustomers().size() == 1);

        Customer customerNew = Customer.builder().name("Cliente 2").businessArea("IT").cpnj("123456").build();
        file.addCustomer(customerNew);
        assertTrue(file.getCustomers().size() == 2);
    }
}
