package br.com.agibank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class Salesman {

    private  String cpf;

    private  String name;

    private BigDecimal salary;
}
