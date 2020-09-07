package com.admxj.signature.dsa;

import com.admxj.signature.SignatureUtil;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 项金
 * @version Id: DsaSignatureUtil, v 0.1 2020/9/6 5:42 下午 项金 Exp $
 */
public class DsaSignatureUtil implements SignatureUtil {
    @Override
    public SignatureKey initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        return new SignatureKey(privateKey, publicKey);
    }

    @Override
    public byte[] sign(byte[] data, byte[] privateKey) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey dsaPrivateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initSign(dsaPrivateKey);
        signature.update(data);
        return signature.sign();
    }

    @Override
    public boolean verify(byte[] data, byte[] sign, byte[] publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
        PublicKey dsaPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initVerify(dsaPublicKey);
        signature.update(data);
        return signature.verify(sign);
    }
}
