package br.com.agibank.Service;

import br.com.agibank.utilities.Console;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileParser implements IFileEventListener {

    private String separatorToken = "ç";
    private String extensionAllowed = ".dat";

    @Override
    public void FileAdded(String fileName) {
        Console.Log("Starting to read file " + fileName);

        try {
            validateExtension(fileName);

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            Console.LogError(e.getMessage());
            return;
        }

        Console.Log("Ending to read file " + fileName);
    }

    private void validateExtension(String fileName) throws Exception {

        if(!(fileName.toLowerCase().endsWith(extensionAllowed)))
            throw new Exception("Invalid format, only allowed " + extensionAllowed + " file extension");

    }
}
