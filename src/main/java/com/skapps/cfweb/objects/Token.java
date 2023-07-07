package com.skapps.cfweb.objects;

import java.util.Random;

public class Token {

    public static final int DEFAULT_TOKEN_SIZE = 20;
    private String value;

    private final char specialChars[] = {'!', '@', '#', '$', '^', '*', '(', ')', '-', '+', '{', '}', '[', ']', ':'};

    public Token() {
        this(DEFAULT_TOKEN_SIZE);
    }
    public Token(int size) {
        value = "";
        for (int i=0; i < size; i++) {
            value += getRandomValue();
        }
    }

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private String generateActivationToke(int stringLength) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            stringBuilder.append(getRandomValue());
        }
        return stringBuilder.toString();
    }


    private char getRandomValue() {
        Random random = new Random();
        int rVal = random.nextInt(3);
        switch (rVal) {
            case 0 : return getRandomNumericValue();
            case 1 : return getRandomLowerCaseValue();
            case 2 : return getRandomUpperCaseValue();
            default: return getRandomValue();
        }
    }
    private char getRandomNumericValue() {
        Random random = new Random();
        return (char) (random.nextInt(9) + 48);
    }

    private char getRandomUpperCaseValue() {
        String letter = "" + getRandomLowerCaseValue();
        letter = letter.toUpperCase();
        return letter.charAt(0);
    }

    private char getRandomLowerCaseValue() {
        Random random = new Random();
        int rVal = random.nextInt(26) + 97;
        return (char) rVal;
    }

    private char getRandomSpecialCharValue() {

        Random random = new Random();
        int rval = random.nextInt(specialChars.length);
        return specialChars[rval];
    }
}
