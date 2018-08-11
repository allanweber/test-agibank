package br.com.agibank.Service;

public class FileAnalizer {

    private String pathComplement = "\\data\\in";
    private String pathRootProperty = "user.home";

    public void AnalisePath(){
        String homeDir = System.getProperty(pathRootProperty);
        homeDir = homeDir.concat(pathComplement);

        WatcherService watcherService = new WatcherService();

        FileParser parser = new FileParser();
        watcherService.registerListener(parser);

        watcherService.start(homeDir);
    }
}
