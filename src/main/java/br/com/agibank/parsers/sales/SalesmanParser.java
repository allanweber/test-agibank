package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Salesman;
import br.com.agibank.utilities.BigDecimalConverter;

public class SalesmanParser {

    public static Salesman parse(String line) throws Exception {

        if(!line.startsWith(Constants.SALESMANTYPE))
            throw new Exception("The line must start with " + Constants.SALESMANTYPE);

        String[] data = line.split(Constants.SEPARATOR);

        Salesman salesman = Salesman.builder()
                .cpf(data[1])
                .name(data[2])
                .salary(BigDecimalConverter.convert(data[3]))
                .build();

        return salesman;
    }

}
