package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.SaleItem;

import java.util.ArrayList;
import java.util.List;

public class SalesItemListParser {

    private static final String SEPARATOR = ",";

    public static List<SaleItem> parse(String line) {

        line = line.replace("[", "").replace("]", "");

        List<SaleItem> itens = new ArrayList<>();

        String[] data = line.split(SEPARATOR);

        for (String item: data){
            itens.add(SaleItemParser.parse(item));
        }

        return itens;
    }
}
