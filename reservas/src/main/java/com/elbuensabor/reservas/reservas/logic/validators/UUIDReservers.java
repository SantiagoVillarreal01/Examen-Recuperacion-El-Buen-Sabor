package com.elbuensabor.reservas.reservas.logic.validators;

import java.security.SecureRandom;
import java.util.Random;

public class UUIDReservers {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final Random random = new SecureRandom();
    
    public static String generateUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    public static String generateRandomCode() {
        StringBuilder codeBuilder = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            codeBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

}
