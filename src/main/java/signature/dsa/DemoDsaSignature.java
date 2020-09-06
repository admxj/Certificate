package signature.dsa;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import signature.SignatureUtil;
import signature.rsa.DemoRsaSignature;
import signature.rsa.RsaSignatureUtil;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author 项金
 * @version Id: DemoDsaSignature, v 0.1 2020/9/6 5:42 下午 项金 Exp $
 */
public class DemoDsaSignature {

    private final static Logger logger = LoggerFactory.getLogger(DemoRsaSignature.class);

    private final static String DATA = "sign data";

    public static void main(String[] args) throws Exception {

        DsaSignatureUtil signatureUtil = new DsaSignatureUtil();

        SignatureUtil.SignatureKey rsaKey = signatureUtil.initKey();
        PublicKey publicKey = rsaKey.getPublicKey();
        PrivateKey privateKey = rsaKey.getPrivateKey();
        logger.debug("public key: {}", publicKey);
        logger.debug("private key: {}", privateKey);

        byte[] sign = signatureUtil.sign(DATA.getBytes(), privateKey.getEncoded());
        logger.info("sign : {}", Hex.encodeHexString(sign));

        boolean verify = signatureUtil.verify((DATA).getBytes(), sign, publicKey.getEncoded());
        logger.info("verify : {}", verify);
    }
}
