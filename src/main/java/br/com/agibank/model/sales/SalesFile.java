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
        salesmen.add(salesman);
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public List<Sale> getSales(){
        return sales;
    }

    public void addSale(Sale sale){
        sales.add(sale);
    }








}
