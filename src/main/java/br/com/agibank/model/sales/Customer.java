package br.com.agibank.model.sales;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private String cpnj;

    private String name;

    private String businessArea;
}
