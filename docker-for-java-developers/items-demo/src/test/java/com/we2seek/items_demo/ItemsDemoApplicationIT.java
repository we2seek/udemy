package com.we2seek.items_demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Integration tests
 */
public class ItemsDemoApplicationIT {

    private static final char EMPTY_CHAR = ' ';
    private static final char FILL_CHAR = '.';
    private static final char[] DOTS = new char[10];

    @Test
    public void someFakeTestOne() throws InterruptedException {
        showDummyText("Test #1");
        Thread.sleep(1000);
    }

    @Test
    public void someFakeTestTwo() throws InterruptedException {
        showDummyText("Test #2");
        Thread.sleep(1000);
    }

    private static void showDummyText(String prefix) throws InterruptedException {
        StringBuilder sb = new StringBuilder(prefix);
        Arrays.fill(DOTS, EMPTY_CHAR);
        for (int i = 0; i < DOTS.length; i++) {
            for (int j = 0; j <= i; j++) {
                DOTS[j] = FILL_CHAR;
            }
            System.out.println(sb.append(' ').append(DOTS));
            sb.setLength(prefix.length());
            Thread.sleep(System.currentTimeMillis() % 500);
        }
    }
}
