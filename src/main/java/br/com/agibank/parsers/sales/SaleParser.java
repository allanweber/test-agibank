package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Sale;
import br.com.agibank.model.sales.SaleItem;
import br.com.agibank.parsers.exceptions.InvalidFileDataSizeException;

import java.util.List;

public class SaleParser {

    public static Sale parse(String line) throws Exception {

        if(!line.startsWith(Constants.SALETYPE))
            throw new Exception("The line must start with " + Constants.SALETYPE);

        String[] data = line.split(Constants.SEPARATOR);

        if(data.length != 4){
            throw new InvalidFileDataSizeException("Sale data size must be 4");
        }

        List<SaleItem> itens = SalesItemListParser.parse(data[2]);

        Sale sale = Sale.builder()
                .id(new Integer(data[1]))
                .itens(itens)
                .salesmanName(data[3])
                .build();

        sale.updateTotal();
        return sale;
    }

}
