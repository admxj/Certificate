package com.admxj.security.asymmetric.rsa;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author 项金
 * @version Id: DemoRsa, v 0.1 2020/9/6 4:21 下午 项金 Exp $
 */
public class DemoRsa {

    private static final Logger logger = LoggerFactory.getLogger(DemoRsa.class);

    private static final String DATA = "security data";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {

        RsaKey rsaKey = RsaUtil.initKey();
        RSAPublicKey publicKey = rsaKey.getPublicKey();
        RSAPrivateCrtKey privateKey = rsaKey.getPrivateKey();

        logger.debug("私钥: {}", privateKey);
        logger.debug("公钥: {}", publicKey);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptData = cipher.doFinal(DATA.getBytes());
        logger.info("Rsa 密文: {}", Hex.encodeHexString(encryptData));

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptData = cipher.doFinal(encryptData);
        logger.info("Rsa 解密明文: {}", new String(decryptData));

    }
}
