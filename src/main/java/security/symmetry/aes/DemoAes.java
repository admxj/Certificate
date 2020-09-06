package security.symmetry.aes;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Base64;

/**
 * @author 项金
 * @version Id: DemoAes, v 0.1 2020/9/6 2:10 上午 项金 Exp $
 */
public class DemoAes {

    private final static Logger logger = LoggerFactory.getLogger(DemoAes.class);

    private static final String encodeData = "Security Data";

    public static void main(String[] args) {
        jdkAes();
        bcAes();
    }

    public static void jdkAes() {
        try {
            // 生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyByte = secretKey.getEncoded();

            // 转换Key
            SecretKeySpec aesKey = new SecretKeySpec(keyByte, "AES");
            logger.debug("jdk aes secretKey: {}", Base64.getEncoder().encodeToString(keyByte));

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encodeData = cipher.doFinal(DemoAes.encodeData.getBytes());
            logger.info("jdk aes encrypt: {}", Base64.getEncoder().encodeToString(encodeData));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decodeData = cipher.doFinal(encodeData);
            logger.info("jdk aes decrypt: {}", new String(decodeData));


        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void bcAes() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            // 生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyByte = secretKey.getEncoded();

            // 转换Key
            SecretKeySpec aesKey = new SecretKeySpec(keyByte, "AES");
            logger.debug("bc aes secretKey: {}", Base64.getEncoder().encodeToString(keyByte));

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encodeData = cipher.doFinal(DemoAes.encodeData.getBytes());
            logger.info("bc aes encrypt: {}", Base64.getEncoder().encodeToString(encodeData));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decodeData = cipher.doFinal(encodeData);
            logger.info("bc aes decrypt: {}", new String(decodeData));


        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
}
