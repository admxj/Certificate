package com.admxj.security.asymmetric.dh;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 出现“Unsupported secret key algorithm：AES”错误的解决办法
 * JDK8 update 161之后，DH的密钥长度至少为512位，但AES算法密钥不能达到这样的长度，长度不一致所以导致报错。
 * 解决的方法： 将 -Djdk.crypto.KeyAgreement.legacyKDF=true 写入JVM系统变量中
 *
 * @author 项金
 * @version Id: DemoDh, v 0.1 2020/9/6 3:00 上午 项金 Exp $
 */
public class DemoDh {

    private static final Logger logger = LoggerFactory.getLogger(DemoDh.class);

    private static final String DATA = "security data";

    private static final Base64.Encoder base64Encoder = Base64.getEncoder();

    public static void main(String[] args) {

        try {
            // 1.初始化发送方密钥
            DhKey senderKey = DhUtil.initKey();
            DHPrivateKey senderDhPrivateKey = senderKey.getDhPrivateKey();
            DHPublicKey senderDhPublicKey = senderKey.getDhPublicKey();
            logger.debug("甲方公钥: {}", Hex.encodeHexString(senderDhPublicKey.getEncoded()));
            logger.debug("甲方私钥: {}", Hex.encodeHexString(senderDhPrivateKey.getEncoded()));


            // 2.初始化接收方密钥
            DhKey receiverDhKey = DhUtil.initKey(senderDhPublicKey.getEncoded());
            DHPrivateKey receiverDhPrivateKey = receiverDhKey.getDhPrivateKey();
            DHPublicKey receiverDhPublicKey = receiverDhKey.getDhPublicKey();
            logger.debug("乙方公钥: {}", Hex.encodeHexString(receiverDhPublicKey.getEncoded()));
            logger.debug("乙方私钥: {}", Hex.encodeHexString(receiverDhPrivateKey.getEncoded()));

            // 3.发送方本地密钥构建
            SecretKey senderSecretKey = DhUtil.getSecretKey(senderDhPrivateKey.getEncoded(), receiverDhPublicKey.getEncoded());
            logger.debug("甲方 secret Key : {}", Hex.encodeHexString(senderSecretKey.getEncoded()));

            // 4.接收方本地密钥构建
            SecretKey receiverSecretKey = DhUtil.getSecretKey(receiverDhPrivateKey.getEncoded(), senderDhPublicKey.getEncoded());
            logger.debug("乙方 secret Key : {}", Hex.encodeHexString(receiverSecretKey.getEncoded()));

            // 5.发送方加密
            byte[] encryptData = DhUtil.encrypt(DATA.getBytes(), senderSecretKey);
            logger.info("甲方加密后数据: {}", base64Encoder.encodeToString(encryptData));

            // 6.接收方解密
            byte[] decryptData = DhUtil.decrypt(encryptData, receiverSecretKey);
            logger.info("乙方解密后数据: {}", new String(decryptData));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

    }
}
