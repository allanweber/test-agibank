package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.SaleItem;
import br.com.agibank.parsers.exceptions.InvalidFileDataSizeException;
import br.com.agibank.utilities.BigDecimalConverter;


public class SaleItemParser {

    private static final String SEPARATOR = "-";

    public static SaleItem parse(String line) throws InvalidFileDataSizeException {
        String[] data = line.split(SEPARATOR);

        if(data.length != 3){
            throw new InvalidFileDataSizeException("Sale item data size must be 4");
        }

        SaleItem item = SaleItem.builder()
                .id(new Integer(data[0]))
                .quantity(BigDecimalConverter.convert(data[1]))
                .price(BigDecimalConverter.convert(data[2]))
                .build();

        return item;
    }
}
