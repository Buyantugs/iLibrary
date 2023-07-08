package com.iLibrary.utils;

import java.awt.*;
import java.util.Random;


public class Util {
    public static final Dimension WINDOW_DIMENSION = new Dimension(639, 500);

    public static String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static int getRandom(int length) {
        return new Random().nextInt(length);
    }
}
