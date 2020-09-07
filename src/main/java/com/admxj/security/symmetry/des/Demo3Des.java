package com.admxj.security.symmetry.des;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author 项金
 * @version Id: Demo2Des, v 0.1 2020/9/6 2:05 上午 项金 Exp $
 */
public class Demo3Des {

    private final static Logger logger = LoggerFactory.getLogger(Demo3Des.class);

    private static final String encodeData = "Security Data";

    public static void main(String[] args) {
//        jdk3Des();
        bc3Des();
    }

    private static void jdk3Des() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            keyGenerator.init(168);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();

            // Key 转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            logger.debug("jdk 3des secret: {}", Base64.getEncoder().encodeToString(convertSecretKey.getEncoded()));

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] bytes = cipher.doFinal(encodeData.getBytes());
            logger.info("jdk 3des encrypt: {}", Hex.encodeHexString(bytes));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] decodeData = cipher.doFinal(bytes);
            logger.info("jdk 3des decrypt: {}", new String(decodeData));

        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }


    private static void bc3Des() {
        try {
            Security.addProvider(new BouncyCastleProvider());

            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede", "BC");
            keyGenerator.init(168);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();

            // Key 转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            logger.debug("bc 3des secret: {}", Base64.getEncoder().encodeToString(convertSecretKey.getEncoded()));

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] bytes = cipher.doFinal(encodeData.getBytes());
            logger.info("bc 3des encrypt: {}", Hex.encodeHexString(bytes));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] decodeData = cipher.doFinal(bytes);
            logger.info("bc 3des decrypt: {}", new String(decodeData));

        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
            logger.error("des error ", e.getCause());
            e.printStackTrace();
        }

    }
}
