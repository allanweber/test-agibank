package br.com.agibank.Service;

import java.io.IOException;

public class FileAnalizer {

    private String pathComplement = "\\data\\in";
    private String pathRootProperty = "user.home";

    public void AnalisePath(){
        String homeDir = System.getProperty(pathRootProperty);
        homeDir = homeDir.concat(pathComplement);

        WatcherService watcherService = new WatcherService();

        FileParser parser = new FileParser();
        watcherService.registerListener(parser);

        try {
            watcherService.start(homeDir);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
