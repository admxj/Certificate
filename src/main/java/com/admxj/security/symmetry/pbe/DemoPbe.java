package com.admxj.security.symmetry.pbe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author 项金
 * @version Id: DemoPbe, v 0.1 2020/9/6 2:27 上午 项金 Exp $
 */
public class DemoPbe {

    private final static Logger logger = LoggerFactory.getLogger(DemoPbe.class);

    public static final String password  = "my password";

    public static final String encryptData  = "encrypt Data";

    public static void main(String[] args) {
        jdkPbe();

    }

    public static void jdkPbe(){

        try {
            // 初始化盐
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = secureRandom.generateSeed(8);

            // 口令与密钥
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            logger.debug("jdk pbe salt : {}", Base64.getEncoder().encodeToString(salt));

            logger.debug("jdk pbe secretKey : {}", new String(secretKey.getEncoded()));

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
            byte[] encryptData = cipher.doFinal(DemoPbe.encryptData.getBytes());
            logger.info("jdk pbe encrypt : {}", Base64.getEncoder().encodeToString(encryptData));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
            byte[] decryptData = cipher.doFinal(encryptData);
            logger.info("jdk pbe decrypt : {}", new String(decryptData));

        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

    }
}
