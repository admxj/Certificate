package certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author 项金
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static final String DATA = "我要加密";

    public static final String PASSWORD = "123456";

    public static final String ALIAS = "www.jikexueyuan.com";

    public static final String CERTIFICATE_PATH = "ssl/jikexueyuan.cer";

    public static final String KEY_STORE_PATH = "ssl/jikexueyuan.keystore";

    private static final Base64.Encoder base64Encoder = Base64.getEncoder();

    public static void main(String[] args) throws Exception {

        PrivateKey privateKey = CertificateUtil.getPrivateKeyFromKeyStore(KEY_STORE_PATH, ALIAS, PASSWORD);

        PublicKey publicKey = CertificateUtil.getPublicKeyFromKeyStore(CERTIFICATE_PATH);

        byte[] encryptData = CertificateUtil.encrypt(DATA.getBytes(), publicKey);

        String encryptBase64Data = base64Encoder.encodeToString(encryptData);
        logger.info("加密后数据: {}", encryptBase64Data);

        byte[] decryptData = CertificateUtil.decrypt(encryptData, privateKey);

        String decryptOriginData = new String(decryptData);
        logger.info("解密后数据: {}", decryptOriginData);

    }

}
