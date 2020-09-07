package com.admxj.certificate;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * @author 项金
 */
public class CertificateUtil {

    public static PrivateKey getPrivateKeyFromKeyStore(String keyStorePath, String alias, String password)
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream keyStoreStream = CertificateUtil.class.getClassLoader().getResourceAsStream(keyStorePath);
        assert keyStoreStream != null;
//		FileInputStream stream = new FileInputStream(keyStorePath);
        keyStore.load(keyStoreStream, password.toCharArray());

        keyStoreStream.close();

        return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
    }

    public static PublicKey getPublicKeyFromKeyStore(String certificatePath) throws Exception {

        CertificateFactory factory = CertificateFactory.getInstance("X.509");
//		FileInputStream stream = new FileInputStream(certificatePath);
        InputStream certificateStream = ClassLoader.getSystemClassLoader().getResourceAsStream(certificatePath);
        assert certificateStream != null;
        Certificate certificate = factory.generateCertificate(certificateStream);
        certificateStream.close();
        return certificate.getPublicKey();
    }

    public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//	
//		CertificateFactory factory = CertificateFactory.getInstance("X.509");
//		FileInputStream stream = new FileInputStream("jikexueyuan.cer");
//		Certificate certificate = factory.generateCertificate(stream);
//		cipher.init(Cipher.ENCRYPT_MODE, certificate);

        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, PrivateKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

}
