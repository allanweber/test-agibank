package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.SalesFile;
import br.com.agibank.parsers.IFileParser;
import br.com.agibank.utilities.Console;

import java.util.List;

public class SalesFileParser implements IFileParser {

    private SalesFile file;

    public SalesFile getFullSaleFile(){
        return  file;
    }

    @Override
    public void parseFile(List<String> lines) {

        file = new SalesFile();

        try {
            for (String line : lines) {

                if(line.startsWith(Constants.SALESMANTYPE)){
                    file.addSalesman(SalesmanParser.parse(line));
                } else if(line.startsWith(Constants.CUSTOMERTYPE)){
                    file.addCustomer(CustomerParser.parse(line));
                } else if(line.startsWith(Constants.SALETYPE)){
                    file.addSale(SaleParser.parse(line));
                } else{
                    String message = "Data type of this line is not valid";
                    Console.LogError(message + ": " + line);
                    throw new Exception(message);
                }

            }

            if(file != null){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
