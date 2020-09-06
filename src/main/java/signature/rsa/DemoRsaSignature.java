package signature.rsa;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import signature.SignatureUtil;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author 项金
 * @version Id: DemoRsaSignature, v 0.1 2020/9/6 5:33 下午 项金 Exp $
 */
public class DemoRsaSignature {

    private final static Logger logger = LoggerFactory.getLogger(DemoRsaSignature.class);

    private final static String DATA = "sign data";

    public static void main(String[] args) throws Exception {

        RsaSignatureUtil rsaSignatureUtil = new RsaSignatureUtil();

        SignatureUtil.SignatureKey rsaKey = rsaSignatureUtil.initKey();
        PublicKey publicKey = rsaKey.getPublicKey();
        PrivateKey privateKey = rsaKey.getPrivateKey();
        logger.debug("public key: {}", publicKey);
        logger.debug("private key: {}", privateKey);

        byte[] sign = rsaSignatureUtil.sign(DATA.getBytes(), privateKey.getEncoded());
        logger.info("sign : {}", Hex.encodeHexString(sign));

        boolean verify = rsaSignatureUtil.verify((DATA).getBytes(), sign, publicKey.getEncoded());
        logger.info("verify : {}", verify);
    }
}
