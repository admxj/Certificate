package com.admxj.security.md;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

/**
 * @author 项金
 */
public class AdmxjMd {
	
	private static String src = "security md";
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("============ jdkMd5 ============");
		jdkMd5();
		System.out.println("============ jdkMd2 ============");
		jdkMd2();
		System.out.println("============ bcMd4 ============");
		bcMd4();
		System.out.println("============ bcMd5 ============");
		bcMd5();
		System.out.println("============ ccMd5 ============");
		ccMd5();
	}
	
	public static void jdkMd5() throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		byte[] md5Bytes = md.digest(src.getBytes());
		String md5 = Hex.encodeHexString(md5Bytes);
		System.out.println("md5: "+ md5);
		
		System.out.println("result: " + "ddd086eec2506afaf34515b9bc2957b2".equals(md5));
	}
	
	public static void jdkMd2() throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD2");
		
		byte[] md2Bytes = md.digest(src.getBytes());
		String md2 = Hex.encodeHexString(md2Bytes);
		System.out.println("md2: "+ md2);
		
		System.out.println("result: " + "0ee18b33f7e8025bdbbc961901ff0a17".equals(md2));
	}
	public static void bcMd5() throws NoSuchAlgorithmException{
		Digest digest = new MD5Digest();
		
		digest.update(src.getBytes(), 0, src.length());
		byte[] md5Byte = new byte[digest.getDigestSize()];
		digest.doFinal(md5Byte, 0);
		
		String md5 = org.bouncycastle.util.encoders.Hex.toHexString(md5Byte);
		System.out.println("md5: " + md5);
		System.out.println("result: " + "ddd086eec2506afaf34515b9bc2957b2".equals(md5));
	}
	public static void bcMd4() throws NoSuchAlgorithmException{
		Digest digest = new MD4Digest();
		
		digest.update(src.getBytes(), 0, src.length());
		byte[] md4Byte = new byte[digest.getDigestSize()];
		digest.doFinal(md4Byte, 0);
		
		String md4 = org.bouncycastle.util.encoders.Hex.toHexString(md4Byte);
		System.out.println("md4: " + md4);
		System.out.println("result: " + "28ac52a7f82b1710a52664875c9b1386".equals(md4));
	}
	public static void bcMd2() throws NoSuchAlgorithmException{
		Digest digest = new MD2Digest();
		
		digest.update(src.getBytes(), 0, src.length());
		byte[] md2Byte = new byte[digest.getDigestSize()];
		digest.doFinal(md2Byte, 0);
		
		String md2 = org.bouncycastle.util.encoders.Hex.toHexString(md2Byte);
		System.out.println("md2: " + md2);
		System.out.println("result: " + "0ee18b33f7e8025bdbbc961901ff0a17".equals(md2));
	}
	public static void ccMd5() throws Exception{
		String md5 = DigestUtils.md5Hex(src.getBytes());
		System.out.println("md5: " + md5);
		System.out.println("result: " + "ddd086eec2506afaf34515b9bc2957b2".equals(md5));

	}
}
