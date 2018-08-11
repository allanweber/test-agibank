package br.com.agibank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Customer {

    private String cpnj;

    private String name;

    private String businessArea;
}
