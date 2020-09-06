package security.asymmetric.rsa;

import lombok.Data;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * @author 项金
 * @version Id: RsaUtil, v 0.1 2020/9/6 4:24 下午 项金 Exp $
 */
public class RsaUtil {

    public static RsaKey initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKeyImpl publicKey = (RSAPublicKeyImpl) keyPair.getPublic();
        RSAPrivateCrtKeyImpl privateKey = (RSAPrivateCrtKeyImpl) keyPair.getPrivate();
        return new RsaKey(publicKey, privateKey);
    }
}

@Data
class RsaKey {
    private RSAPublicKeyImpl publicKey;
    private RSAPrivateCrtKeyImpl privateKey;

    public RsaKey(RSAPublicKeyImpl publicKey, RSAPrivateCrtKeyImpl privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
