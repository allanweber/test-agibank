package br.com.agibank.model.sales;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Sale {

    private Integer id;

    private List<SaleItem> itens;

    private Salesman salesman;

    private String salesmanName;

    @Setter(AccessLevel.PRIVATE)
    private BigDecimal total;

    public void updateTotal() {
        this.setTotal(new BigDecimal(0));

        BigDecimal sum = new BigDecimal(0);
        for (SaleItem item: itens) {
            sum = sum.add(item.getPrice().multiply(item.getQuantity()));
        }

        setTotal(sum);
    }
}
