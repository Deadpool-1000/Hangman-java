package utils.password;

import io.github.cdimascio.dotenv.Dotenv;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Password {
    private static final int ITERATION_COUNT;
    private static final int KEY_LENGTH;

    static {
        Dotenv dt = Dotenv.load();
        ITERATION_COUNT = Integer.parseInt(dt.get("ITERATION_COUNT"));
        KEY_LENGTH = Integer.parseInt(dt.get("KEY_LENGTH"));
    }
    public static Object[] hashPassword(String password) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String hashedPassword = Base64.getEncoder().encodeToString(hash);

        return new Object[]{hashedPassword, salt};
    }

    public static boolean verifyPassword(String inputPassword, String storedHash, byte[] salt) throws Exception{
        KeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String newHash = Base64.getEncoder().encodeToString(hash);

        return newHash.equals(storedHash);
    }
}
