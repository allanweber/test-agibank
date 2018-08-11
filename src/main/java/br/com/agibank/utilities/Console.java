package br.com.agibank.utilities;

public class Console {
    public static void Log(Object obj){
        System.out.println("\033[32;1;2m"+ obj.toString() +"\033[0m");
    }

    public static void LogError(Object obj){
        System.out.println("\033[31;1m" + obj.toString() + "\033[0m");
    }
}
