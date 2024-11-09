package com.jetbrains.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class HashPassword {


    private static final String FIXED_SALT = "1234567890";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";


    public static String hashPassword(String password) {
        try {
            // Combine password with the fixed salt
            KeySpec spec = new PBEKeySpec(password.toCharArray(), FIXED_SALT.getBytes(), ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Method to verify the password
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hashToVerify = hashPassword(password);
        return hashToVerify.equals(hashedPassword);
    }
}