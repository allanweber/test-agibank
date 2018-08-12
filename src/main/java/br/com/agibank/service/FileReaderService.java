package br.com.agibank.service;

import br.com.agibank.parsers.IFileParser;
import br.com.agibank.utilities.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService implements IFileEventListener {

    private String extensionAllowed = ".dat";
    private IFileParser fileParser;

    public FileReaderService(IFileParser fileParser){
        this.fileParser = fileParser;
    }

    @Override
    public void FileAdded(String fileName) {
        Console.Log("Starting to read file " + fileName);

        try {
            validateExtension(fileName);

            List<String> lines  = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(!line.isEmpty())
                        lines.add(line);
                }
            }

            if(lines.size() > 0){
                fileParser.parseFile(lines);
                Path p = Paths.get(fileName);
                String file = p.getFileName().toString();
                fileParser.saveProcessedFile(file);
            }

        } catch (Exception e) {
            Console.LogError(e.getMessage());
            return;
        }

        Console.Log("Ending to read file " + fileName);
    }

    public void validateExtension(String fileName) throws Exception {

        if(!(fileName.toLowerCase().endsWith(extensionAllowed)))
            throw new Exception("Invalid format, only allowed " + extensionAllowed + " file extension");

    }
}
