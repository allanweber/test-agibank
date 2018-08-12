package br.com.agibank.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterService {

    private BufferedWriter writer;

    public FileWriterService(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public FileWriterService writeLine(String line) throws IOException {

        writer.write(line);
        writer.write("\r\n");
        return this;
    }

    public void done() throws IOException {
        writer.close();
    }

}
