package certificate;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Main {
	
	public static final String DATA = "我要加密";
	
	public static final String password = "123456";
	
	public static final String alias = "www.jikexueyuan.com";
	
	public static final String certificatePath = "ssl/jikexueyuan.cer";
	
	public static final String keyStorePath = "ssl/jikexueyuan.keystore";
	
	
	public static void main(String[] args) throws Exception {
		
		PrivateKey privateKey = CertificateUtil.getPrivateKeyFromKeyStore(keyStorePath, alias, password);
		
		PublicKey publicKey = CertificateUtil.getPublicKeyFromKeyStore(certificatePath);
		
		byte[] cipher = CertificateUtil.encrypt(DATA.getBytes(), publicKey);
		
		System.out.println("加密后数据: " + cipher.hashCode());
		
		byte[] plain = CertificateUtil.decrypt(cipher, privateKey);
		
		System.out.println("解密后数据: "+new String(plain));
		
		
	}

}
