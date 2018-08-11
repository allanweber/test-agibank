package br.com.agibank.model.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FullSaleFile {

    public FullSaleFile() {
        Salesmen = new ArrayList<>();
        Customers = new ArrayList<>();
        Sales = new ArrayList<>();
    }

    public List<Salesman> Salesmen;

    public List<Customer> Customers;

    public List<Sale> Sales;
}
