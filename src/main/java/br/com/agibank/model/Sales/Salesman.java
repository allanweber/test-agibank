package br.com.agibank.model.Sales;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Salesman {

    private  String cpf;

    private  String name;

    private BigDecimal salary;
}
