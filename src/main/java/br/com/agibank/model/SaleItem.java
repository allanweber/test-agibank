package br.com.agibank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class SaleItem {

    private Integer id;

    private BigDecimal quantity;

    private BigDecimal price;

}
