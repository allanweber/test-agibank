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
    public void shouldSetSalesmanToSaleAddingSaleman() throws FileDataException {
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
                                                .price(BigDecimal.valueOf(2))
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
        assertTrue(sale.getSalesman().getSalesAmount().doubleValue() == 7.65);

        sale = file.getSales().get(1);
        assertTrue(sale.getSalesman().getName().equals("Vendedor 2"));
        assertTrue(sale.getSalesman().getSalesAmount().doubleValue() == 6);
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

    @Test
    public void shouldSetSalesmanToSaleAddingSale() throws FileDataException{
        SalesFile file = new SalesFile();

        Salesman vendedor1 = Salesman.builder()
                .cpf("2345")
                .name("Vendedor 1")
                .salary(BigDecimal.valueOf(1234.56))
                .build();
        file.addSalesman(vendedor1);

        Salesman vendedor2 = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 2")
                .salary(BigDecimal.valueOf(1234.56))
                .build();
        file.addSalesman(vendedor2);

        assertTrue(file.getSalesmen().size() == 2);

        Sale noMansSale = Sale.builder()
                .id(1)
                .salesmanName("Any Salesman")
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

        file.addSale(noMansSale);
        Sale selected = file.getSales().get(0);
        assertTrue(file.getSales().size() == 1);
        assertTrue(selected.getSalesmanName().equals("Any Salesman"));
        assertTrue(selected.getSalesman() == null);

        Sale noVendedor1 = Sale.builder()
                .id(1)
                .salesmanName("Vendedor 1")
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

        file.addSale(noVendedor1);
        selected = file.getSales().get(1);
        assertTrue(file.getSales().size() == 2);
        assertTrue(selected.getSalesmanName().equals("Vendedor 1"));
        assertTrue(selected.getSalesman() != null);
        assertTrue(selected.getSalesman().getCpf().equals("2345"));
        assertTrue(selected.getSalesman().getSalesAmount().doubleValue() == 7.65);

        selected = file.getSales().get(0);
        assertTrue(selected.getSalesmanName().equals("Any Salesman"));
        assertTrue(selected.getSalesman() == null);
    }

    @Test
    public void shouldValidateTotalOfSales() throws FileDataException {
        SalesFile salesFile = new SalesFile();
        salesFile.addSale(
                Sale.builder()
                        .id(2)
                        .salesmanName("Vendedor")
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(5))
                                                .quantity(BigDecimal.valueOf(6.78))
                                                .build(),
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(3))
                                                .quantity(BigDecimal.valueOf(4))
                                                .build()
                                )
                        )
                        .build()
        );

        salesFile.addSale(
                Sale.builder()
                        .id(1)
                        .salesmanName("Vendedor")
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(10))
                                                .quantity(BigDecimal.valueOf(6.78))
                                                .build(),
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(2))
                                                .quantity(BigDecimal.valueOf(5.43))
                                                .build()
                                )
                        )
                        .build()
        );

        salesFile.addSale(
                Sale.builder()
                        .id(3)
                        .salesmanName("Vendedor")
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(1))
                                                .quantity(BigDecimal.valueOf(6.78))
                                                .build(),
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(2))
                                                .quantity(BigDecimal.valueOf(4.32))
                                                .build()
                                )
                        )
                        .build()
        );

        assertTrue(salesFile.getSales().get(0).getTotal().doubleValue() == 45.9);
        assertTrue(salesFile.getSales().get(1).getTotal().doubleValue() == 78.66);
        assertTrue(salesFile.getSales().get(2).getTotal().doubleValue() == 15.42);
    }

    @Test
    public void shouldValidateTheBestSale() throws FileDataException {
        SalesFile salesFile = new SalesFile();
        salesFile.addSale(
                Sale.builder()
                        .id(1)
                        .salesmanName("Vendedor")
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(5))
                                                .quantity(BigDecimal.valueOf(6.78))
                                                .build(),
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(3))
                                                .quantity(BigDecimal.valueOf(4))
                                                .build()
                                )
                        )
                        .build()
        );

        salesFile.addSale(
                Sale.builder()
                        .id(2)
                        .salesmanName("Vendedor")
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(10))
                                                .quantity(BigDecimal.valueOf(6.78))
                                                .build(),
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(2))
                                                .quantity(BigDecimal.valueOf(5.43))
                                                .build()
                                )
                        )
                        .build()
        );

        salesFile.addSale(
                Sale.builder()
                        .id(3)
                        .salesmanName("Vendedor")
                        .itens(
                                Arrays.asList(
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(1))
                                                .quantity(BigDecimal.valueOf(6.78))
                                                .build(),
                                        SaleItem.builder()
                                                .id(1)
                                                .price(BigDecimal.valueOf(2))
                                                .quantity(BigDecimal.valueOf(4.32))
                                                .build()
                                )
                        )
                        .build()
        );

        Sale bestSale = salesFile.getGreaterSale();
        assertTrue(bestSale.getId().equals(2));
    }

    @Test
    public void shouldValidateTheWorstSalesman() throws FileDataException{
        SalesFile file = new SalesFile();

        Salesman vendedor1 = Salesman.builder()
                .cpf("2345")
                .name("Vendedor 1")
                .salary(BigDecimal.valueOf(1234.56))
                .build();
        file.addSalesman(vendedor1);

        Salesman vendedor2 = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 2")
                .salary(BigDecimal.valueOf(1234.56))
                .build();
        file.addSalesman(vendedor2);

        assertTrue(file.getSalesmen().size() == 2);

        Sale sell1 = Sale.builder()
                .id(1)
                .salesman(vendedor1)
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

        file.addSale(sell1);

        Sale sell2 = Sale.builder()
                .id(1)
                .salesman(vendedor2)
                .itens(
                        Arrays.asList(
                                SaleItem.builder()
                                        .id(1)
                                        .price(BigDecimal.valueOf(2.55))
                                        .quantity(BigDecimal.valueOf(1))
                                        .build()
                        )
                )
                .build();

        file.addSale(sell2);

        Salesman worst = file.getWorstSalesman();

        assertTrue(worst.getName().equals("Vendedor 2"));
        assertTrue(worst.getSalesAmount().doubleValue() == 2.55);
    }

    @Test
    public void shouldValidateTheWorstSalesmanWithZeroSells() throws FileDataException{
        SalesFile file = new SalesFile();

        Salesman vendedor1 = Salesman.builder()
                .cpf("2345")
                .name("Vendedor 1")
                .salary(BigDecimal.valueOf(1234.56))
                .build();
        file.addSalesman(vendedor1);

        Salesman vendedor2 = Salesman.builder()
                .cpf("12345")
                .name("Vendedor 2")
                .salary(BigDecimal.valueOf(1234.56))
                .build();
        file.addSalesman(vendedor2);

        Salesman vendedor3 = Salesman.builder()
                .cpf("34567")
                .name("Vendedor 3")
                .salary(BigDecimal.valueOf(1234.56))
                .build();
        file.addSalesman(vendedor3);

        assertTrue(file.getSalesmen().size() == 3);

        Sale sell1 = Sale.builder()
                .id(1)
                .salesman(vendedor1)
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

        file.addSale(sell1);

        Sale sell2 = Sale.builder()
                .id(1)
                .salesman(vendedor2)
                .itens(
                        Arrays.asList(
                                SaleItem.builder()
                                        .id(1)
                                        .price(BigDecimal.valueOf(2.55))
                                        .quantity(BigDecimal.valueOf(1))
                                        .build()
                        )
                )
                .build();

        file.addSale(sell2);

        Salesman worst = file.getWorstSalesman();

        assertTrue(worst.getName().equals("Vendedor 3"));
        assertTrue(worst.getSalesAmount().doubleValue() == 0);
    }
}
