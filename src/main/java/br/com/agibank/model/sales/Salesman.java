package br.com.agibank.model.sales;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Builder
public class Salesman {

    private  String cpf;

    private  String name;

    private BigDecimal salary;

    @Setter(AccessLevel.PRIVATE)
    private BigDecimal salesAmount;

    public void addSaleAmount(BigDecimal value){
        if(salesAmount == null)
            salesAmount = new BigDecimal(0);
        salesAmount = salesAmount.add(value);
    }
}
