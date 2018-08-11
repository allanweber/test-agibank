package br.com.agibank.model.sales;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaleItem {

    private Integer id;

    private BigDecimal quantity;

    private BigDecimal price;

}
