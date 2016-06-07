package demo.encrypters;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by nguyen.dang on 6/7/16.
 */
public class DesEncrypter {

    private static final String SALT = "my_secret_salt";

    public static String encrypt(String code, String keyText) {
        try {
            keyText = keyText.concat(SALT);
            final DESKeySpec keySpec = new DESKeySpec(keyText.getBytes("UTF8"));
            final SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("DES");
            final SecretKey key = keyFactory.generateSecret(keySpec);
            final Cipher cipher = Cipher.getInstance("DES"); // cipher is not
            // thread safe
            cipher.init(Cipher.ENCRYPT_MODE, key);
            final char[] encoded = Hex.encode(cipher.doFinal(code.getBytes()));
            return new String(encoded);
        } catch (final Exception e) {
//            log.error(e.getMessage(), e.fillInStackTrace(), e);
        }
        return null;
    }

    public static String decrypt(String code, String keyText) {
        try {
            keyText = keyText.concat(SALT);
            final DESKeySpec keySpec = new DESKeySpec(keyText.getBytes("UTF8"));
            final SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("DES");
            final SecretKey key = keyFactory.generateSecret(keySpec);
            final Cipher cipher = Cipher.getInstance("DES"); // cipher is not
            // thread
            // safe
            cipher.init(Cipher.DECRYPT_MODE, key);
            final byte[] decripted = cipher.doFinal(Hex.decode(code));
            return new String(decripted);
        } catch (final Exception e) {
//            log.error(e.getMessage(), e.fillInStackTrace(), e);
        }
        return null;
    }
}
