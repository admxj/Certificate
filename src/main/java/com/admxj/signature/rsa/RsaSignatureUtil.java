package com.admxj.signature.rsa;

import com.admxj.signature.SignatureUtil;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * The type Rsa com.admxj.signature util.
 *
 * @author 项金
 * @version Id : RsaSignatureUtil, v 0.1 2020/9/6 5:24 下午 项金 Exp $
 */
public class RsaSignatureUtil implements SignatureUtil {

    /**
     * 生成密钥对
     *
     * @return rsa key
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    @Override
    public SignatureKey initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        return new SignatureKey(privateKey, publicKey);
    }


    @Override
    public byte[] sign(byte[] data, byte[] privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey rsaPrivateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance("MD5withRSA");

        signature.initSign(rsaPrivateKey);
        signature.update(data);
        return signature.sign();
    }

    @Override
    public boolean verify(byte[] data, byte[] sign, byte[] publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        PublicKey rsaPublicKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(rsaPublicKey);
        signature.update(data);
        return signature.verify(sign);
    }
}
