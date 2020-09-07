package com.admxj.security.symmetry.des;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author 项金
 * @version Id: DesDemo, v 0.1 2020/9/6 1:25 上午 项金 Exp $
 */
public class DesDemo {

    private final static Logger logger = LoggerFactory.getLogger(DesDemo.class);

    private static final String encodeData = "Security Data";

    public static void main(String[] args) {
        jdkDes();
        bcDes();
    }

    public static void jdkDes() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();

            // Key 转换
            DESKeySpec desKeySpec = new DESKeySpec(byteKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            logger.debug("jdk des secret: {}", Base64.getEncoder().encodeToString(convertSecretKey.getEncoded()));

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] bytes = cipher.doFinal(encodeData.getBytes());
            logger.info("jdk des encrypt: {}", Hex.encodeHexString(bytes));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] decodeData = cipher.doFinal(bytes);
            logger.info("jdk des decrypt: {}", new String(decodeData));

        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void bcDes() {
        try {
            Security.addProvider(new BouncyCastleProvider());

            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();

            // Key 转换
            DESKeySpec desKeySpec = new DESKeySpec(byteKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            logger.debug("bc des secret: {}", Base64.getEncoder().encodeToString(convertSecretKey.getEncoded()));

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] bytes = cipher.doFinal(encodeData.getBytes());
            logger.info("bc des encrypt: {}", Hex.encodeHexString(bytes));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] decodeData = cipher.doFinal(bytes);
            logger.info("bc des decrypt: {}", new String(decodeData));

        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
            logger.error("des error ", e.getCause());
            e.printStackTrace();
        }
    }

}
