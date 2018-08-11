package br.com.agibank.app;

import br.com.agibank.Service.FileAnalizer;

public class Executer {

    public static void main(String[] args) {

        try{
            FileAnalizer analizer = new FileAnalizer();
            analizer.AnalisePath();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
