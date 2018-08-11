package br.com.agibank.model.Sales;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Sale {

    private Integer id;

    private List<SaleItem> itens;

    private Salesman salesman;

}
