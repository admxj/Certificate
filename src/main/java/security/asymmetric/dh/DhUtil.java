package security.asymmetric.dh;

import lombok.Data;

import javax.crypto.*;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 项金
 * @version Id: DhUtil, v 0.1 2020/9/6 3:26 下午 项金 Exp $
 */
public class DhUtil {

    private static KeyPairGenerator keyPairGenerator;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("DH");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    // 密钥长度
    private static final int DH_KEY_SIZE = 512;

    public static DhKey initKey() throws NoSuchAlgorithmException {
        // 实例化密钥生成器 默认是1024  64的倍数
        keyPairGenerator.initialize(DH_KEY_SIZE);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取密钥
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        return new DhKey(publicKey, privateKey);
    }

    public static DhKey initKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        // 公钥转换为publicKey
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        DHPublicKey dhPublicKey = (DHPublicKey) keyFactory.generatePublic(keySpec);
        // 解析甲方公钥得到参数
        DHParameterSpec dhParameterSpec = dhPublicKey.getParams();
        // 使用甲方公钥参数生成到密钥对
        keyPairGenerator.initialize(dhParameterSpec);
        KeyPair receiveKeyPair = keyPairGenerator.generateKeyPair();
        DHPublicKey publicKey = (DHPublicKey) receiveKeyPair.getPublic();
        DHPrivateKey privateKey = (DHPrivateKey) receiveKeyPair.getPrivate();
        return new DhKey(publicKey, privateKey);
    }

    public static SecretKey getSecretKey(byte[] privateKey, byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        // 公钥数组转换为publicKey
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        PublicKey dhPublicKey = keyFactory.generatePublic(keySpec);
        // 私钥数组转换为privateKey
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey dhPrivateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 生成SecretKey
        KeyAgreement ketAgreement = KeyAgreement.getInstance("DH");
        ketAgreement.init(dhPrivateKey);
        ketAgreement.doPhase(dhPublicKey, true);
        return ketAgreement.generateSecret("AES");
    }

    public static byte[] encrypt(byte[] data, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

}

@Data
class DhKey {
    DHPublicKey dhPublicKey;
    DHPrivateKey dhPrivateKey;

    public DhKey(DHPublicKey dhPublicKey, DHPrivateKey dhPrivateKey) {
        this.dhPublicKey = dhPublicKey;
        this.dhPrivateKey = dhPrivateKey;
    }
}