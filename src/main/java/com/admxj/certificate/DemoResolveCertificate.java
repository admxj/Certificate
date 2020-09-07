package com.admxj.certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @author jin.xiang
 * @version Id: DemoResolveCertificate, v 0.1 2020/9/7 10:14 上午 jin.xiang Exp $
 */
public class DemoResolveCertificate {

    private static final Logger logger = LoggerFactory.getLogger(DemoResolveCertificate.class);

    public static void main(String[] args) throws Exception {

        X509Certificate certificate = resolveFile("ssl/jike.crt");
        X509Certificate caCertificate = resolveFile("ssl/admxj-ca.crt");

        boolean verify = verifyCertificate(certificate, caCertificate.getPublicKey());
        logger.info("verify : " + verify);
    }

    private static boolean verifyCertificate(Certificate caCertificate, PublicKey publicKey) {
        try {

            caCertificate.verify(publicKey);
            return true;
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static X509Certificate resolveFile(String path) throws IOException, CertificateException {
        InputStream certificateStream = DemoResolveCertificate.class.getClassLoader().getResourceAsStream(path);

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(certificateStream);
        PublicKey data = certificate.getPublicKey();
        byte[] signature = certificate.getSignature();

//        System.out.println(certificate.toString());
//        System.out.println("data : " + data);
//        System.out.println("signature : " + signature);

        return certificate;
    }

    private static void resolveHttp() throws IOException {
        URL url = new URL("https://www.baidu.com");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.connect();
        for (Certificate certificate : connection.getServerCertificates()) {
            //第一个就是服务器本身证书，后续的是证书链上的其他证书
            X509Certificate x509Certificate = (X509Certificate) certificate;
            System.out.println(x509Certificate.getSubjectDN());
            //有效期开始时间
            System.out.println(x509Certificate.getNotBefore());
            //有效期结束时间
            System.out.println(x509Certificate.getNotAfter());
        }
        connection.disconnect();
    }
}
