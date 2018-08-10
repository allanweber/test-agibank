package br.com.agibank.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExecuterTest {
    @Test
    public void testAppHasAGreeting() {
        Executer classUnderTest = new Executer();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}