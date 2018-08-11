package br.com.agibank.app;

import br.com.agibank.Service.FileAnalizerService;

public class Executer {

    public static void main(String[] args) {

        try{
            FileAnalizerService analizer = new FileAnalizerService();
            analizer.AnalisePath();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
