package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.FullSaleFile;
import br.com.agibank.parsers.IFileParser;
import br.com.agibank.utilities.Console;

import java.util.List;

public class SalesFileParser implements IFileParser {

    private FullSaleFile file;

    public FullSaleFile getFullSaleFile(){
        return  file;
    }

    @Override
    public void parseFile(List<String> lines) {

        file = new FullSaleFile();

        try {
            for (String line : lines) {

                if(line.startsWith(Constants.SALESMANTYPE)){
                    file.Salesmen.add(SalesmanParser.parse(line));
                } else if(line.startsWith(Constants.CUSTOMERTYPE)){
                    file.Customers.add(CustomerParser.parse(line));
                } else if(line.startsWith(Constants.SALETYPE)){
                    file.Sales.add(SaleParser.parse(line));
                } else{
                    String message = "Data type of this line is not valid";
                    Console.LogError(message + ": " + line);
                    throw new Exception(message);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
