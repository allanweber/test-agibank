package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Customer;
import br.com.agibank.parsers.exceptions.InvalidFileDataSizeException;

public class CustomerParser {

    public static Customer parse(String line) throws Exception {

        if(!line.startsWith(Constants.CUSTOMERTYPE))
            throw new Exception("The line must start with " + Constants.CUSTOMERTYPE);

        String[] data = line.split(Constants.SEPARATOR);

        if(data.length != 4){
            throw new InvalidFileDataSizeException("Customer data size must be 4: " + line);
        }

        Customer customer = Customer.builder()
                .cpnj(data[1])
                .name(data[2])
                .businessArea(data[3])
                .build();

        return customer;
    }

}
