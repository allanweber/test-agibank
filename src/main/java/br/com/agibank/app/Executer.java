package br.com.agibank.app;

public class Executer {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new Executer().getGreeting());
    }
}
