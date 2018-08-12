package br.com.agibank.parsers.sales;

import br.com.agibank.model.sales.Sale;
import br.com.agibank.model.sales.SalesFile;
import br.com.agibank.model.sales.Salesman;
import br.com.agibank.parsers.IFileParser;
import br.com.agibank.service.FileWriterService;
import br.com.agibank.utilities.Console;

import java.io.IOException;
import java.util.List;

public class SalesFileParser implements IFileParser {

    private SalesFile file;
    private String writePath;

    public SalesFile getFullSaleFile(){
        return  file;
    }

    public SalesFileParser(String writePath){
        this.writePath = writePath;
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveProcessedFile(String fileName){
        try {

            String wroteFile = writePath.concat("\\").concat(fileName.toLowerCase().replace(".dat", ".done.dat"));

            FileWriterService writer = new  FileWriterService(wroteFile);

            writer.writeLine(String.format("Quantidade de clientes no arquivo de entrada: %s", file.getCustomers().size()));

            writer.writeLine(String.format("Quantidade de vendedores no arquivo de entrada: %s", file.getSalesmen().size()));

            Sale greater = file.getGreaterSale();
            writer.writeLine(String.format("ID da venda mais cara: %s", greater != null ? greater.getId() : "N/A"));

            Salesman worst = file.getWorstSalesman();
            writer.writeLine(String.format("O pior vendedor: %s", worst != null ? worst.getName() : "N/A"));

            writer.done();

        } catch (IOException e) {
            Console.LogError("Erro ao escrever arquivo: " + e.getMessage());
        }
    }
}
