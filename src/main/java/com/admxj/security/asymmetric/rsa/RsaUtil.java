package com.admxj.security.asymmetric.rsa;

import lombok.Data;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author 项金
 * @version Id: RsaUtil, v 0.1 2020/9/6 4:24 下午 项金 Exp $
 */
public class RsaUtil {

    public static RsaKey initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey) keyPair.getPrivate();
        return new RsaKey(publicKey, privateKey);
    }
}

@Data
class RsaKey {
    private RSAPublicKey publicKey;
    private RSAPrivateCrtKey privateKey;

    public RsaKey(RSAPublicKey publicKey, RSAPrivateCrtKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
