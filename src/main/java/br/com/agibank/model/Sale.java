package br.com.agibank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class Sale {

    private Integer id;

    private List<SaleItem> itens;

    private Salesman salesman;

}
