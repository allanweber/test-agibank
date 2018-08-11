package br.com.agibank.model.sales;


import java.util.ArrayList;
import java.util.List;

public class SalesFile {

    private List<Salesman> salesmen;
    private List<Customer> customers;
    private List<Sale> sales;

    public SalesFile() {
        salesmen = new ArrayList<>();
        customers = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public List<Salesman> getSalesmen(){
        return salesmen;
    }

    public void addSalesman(Salesman salesman){

        for (Salesman man: salesmen){
            if(man.equals(salesman))
                return;
        }

        salesmen.add(salesman);

        Sale selected =  sales.stream().filter((sale) ->
                sale.getSalesman() == null && sale.getSalesmanName().equals(salesman.getName()))
                .findFirst()
                .orElse(null);

        if(selected != null){
            selected.setSalesman(salesman);
        }

    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public void addCustomer(Customer customer){

        for (Customer current: customers){
            if(current.equals(customer))
                return;
        }

        customers.add(customer);
    }

    public List<Sale> getSales(){
        return sales;
    }

    public void addSale(Sale sale){
        sales.add(sale);
    }








}
