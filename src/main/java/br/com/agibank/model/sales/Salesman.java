package br.com.agibank.model.sales;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Salesman {

    public Salesman() {
        salesAmount = new BigDecimal(0);
    }

    private String cpf;

    private String name;

    private BigDecimal salary;

    private BigDecimal salesAmount;

    public BigDecimal getSalesAmount(){
        if(salesAmount == null)
            salesAmount = new BigDecimal(0);
        return  salesAmount;
    }

    public  void getSalesAmount(BigDecimal value) {
        salesAmount = value;
    }

    public void addSaleAmount(BigDecimal value) {
        if(salesAmount == null)
            salesAmount = new BigDecimal(0);
        salesAmount = salesAmount.add(value);
    }
}
