package br.com.agibank.service;

import br.com.agibank.parsers.sales.SalesParser;

import java.io.IOException;

public class FileAnalizerService {

    private String pathComplement = "\\data\\in";
    private String pathRootProperty = "user.home";

    public void AnalisePath(){
        String homeDir = System.getProperty(pathRootProperty);
        homeDir = homeDir.concat(pathComplement);

        WatcherService watcherService = new WatcherService();

        SalesParser parser = new SalesParser();
        FileReaderService reader = new FileReaderService(parser);
        watcherService.registerListener(reader);

        try {
            watcherService.start(homeDir);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}