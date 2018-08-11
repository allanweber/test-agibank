package br.com.agibank.model;

import br.com.agibank.model.sales.Sale;
import br.com.agibank.model.sales.SaleItem;
import br.com.agibank.model.sales.Salesman;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BuilderModelTest {

    @Test
    public void BuildModelTest() {

        Sale sale = Sale.builder()
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
                                        .build(),
                                SaleItem.builder()
                                        .id(2)
                                        .price(BigDecimal.valueOf(5))
                                        .quantity(BigDecimal.valueOf(6.78))
                                        .build(),
                                SaleItem.builder()
                                        .id(2)
                                        .price(BigDecimal.valueOf(7.8))
                                        .quantity(BigDecimal.valueOf(4.32))
                                        .build()
                        )
                )
                .build();

        assertTrue("Deveria ter 3 itens", sale.getItens().size() == 3);

        SaleItem item = sale.getItens().get(0);
        BigDecimal total = item.getQuantity().multiply(item.getPrice());
        assertTrue("Total do item 1 deveria ser 7.65 mas era " + total, total.doubleValue() == 7.65);
    }

}