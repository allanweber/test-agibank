package br.com.agibank.Parsers;

import br.com.agibank.utilities.Console;

import java.util.Arrays;
import java.util.List;

public class SalesParser implements IFileParser {

    private String separator = "ç";

    private List<String> dataTypes = Arrays.asList("001", "002", "003");

    @Override
    public void parseFile(List<String> lines) {

        try {
            for (String line : lines) {
                validateLineDateType(line);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void validateLineDateType(String line) throws Exception {
        String message = "Data type of this line is not valid";
        for (String type : dataTypes) {
            if (!line.startsWith(type)) {
                Console.LogError(message + ": " + line);
                throw new Exception(message);
            }
        }
    }
}
