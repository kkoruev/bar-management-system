package bg.unisofia.fmi.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class EncryptionUtils {

    final static Random r = new SecureRandom();

    public static String generateSalt() {
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String getHashedPassword(String password, String salt) {
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-512");
            password = password + salt;
            password = Base64.getEncoder().encodeToString(mda.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            //TODO: Log this exception, normally it shouldn't happen, because SHA-512 exists
        }

        return password;
    }
}
