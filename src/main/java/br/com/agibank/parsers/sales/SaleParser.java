package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Sale;
import br.com.agibank.model.sales.SaleItem;

import java.util.List;

public class SaleParser {

    public static Sale parse(String line) throws Exception {

        if(!line.startsWith(Constants.SALETYPE))
            throw new Exception("The line must start with " + Constants.SALETYPE);

        String[] data = line.split(Constants.SEPARATOR);

        List<SaleItem> itens = SalesItemListParser.parse(data[2]);

        Sale sale = Sale.builder()
                .id(new Integer(data[1]))
                .itens(itens)
                .salesmanName(data[3])
                .build();

        return sale;
    }

}
