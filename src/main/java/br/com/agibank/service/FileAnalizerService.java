package br.com.agibank.service;

import br.com.agibank.parsers.sales.SalesFileParser;

import java.io.IOException;

public class FileAnalizerService {

    private String readPath = "\\data\\in";
    private String writePath = "\\data\\out";
    private String pathRootProperty = "user.home";

    public void AnalisePath(){
        String homeDir = System.getProperty(pathRootProperty);

        readPath = homeDir.concat(readPath);
        writePath =  homeDir.concat(writePath);

        WatcherService watcherService = new WatcherService();

        SalesFileParser parser = new SalesFileParser(writePath);
        FileReaderService reader = new FileReaderService(parser);
        watcherService.registerListener(reader);

        try {
            watcherService.start(readPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
