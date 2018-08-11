package br.com.agibank.Service;

import br.com.agibank.utilities.Console;

public class FileParser implements IFileEventListener {

    private String separatorToken = "ç";

    @Override
    public void FileAdded(String fileName) {
        Console.Log("Starting to read file " + fileName);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Console.Log("Ending to read file " + fileName);
    }
}
