package certificate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CertificateUtil {

	public static PrivateKey getPrivateKeyFromKeyStore(String keyStorePath, String alias, String password) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
		
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream stream = new FileInputStream(keyStorePath);
		keyStore.load(stream, password.toCharArray());
		stream.close();

		PrivateKey key = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
		return key;
	}

	public static PublicKey getPublicKeyFromKeyStore(String certificatePath) throws CertificateException, IOException {
		
		CertificateFactory factory = CertificateFactory.getInstance("X.509");
		FileInputStream stream = new FileInputStream(certificatePath);
		Certificate certificate = factory.generateCertificate(stream);
		stream.close();
		
		PublicKey publicKey = certificate.getPublicKey();
		return publicKey;
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

	public static byte[] decrypt(byte[] data, PrivateKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		
		return cipher.doFinal(data);
	}

}
