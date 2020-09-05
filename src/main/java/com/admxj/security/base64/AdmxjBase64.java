package com.admxj.security.base64;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * @author 项金
 */
public class AdmxjBase64 {
	
	private static String src = "security base64";
	
	public static void main(String[] args) {
		System.out.println("===============jdkBase64===============");
		jdkBase64();
		System.out.println("===============commonsCodecBase64===============");
		commonsCodecBase64();
		System.out.println("===============bouncyCastle64===============");
		bouncyCastle64();
	}
	
	public static void jdkBase64(){
		Encoder encoder = Base64.getEncoder();
		
		Decoder decoder = Base64.getDecoder();
		
		String encode = encoder.encodeToString(src.getBytes());
		
		System.out.println("encode: " + encode);
		
		byte[] decode = decoder.decode(encode);
		
		System.out.println("decode: " + new String(decode));
	}
	
	public static void commonsCodecBase64() {
		
		String encoder = org.apache.commons.codec.binary.Base64.encodeBase64String(src.getBytes());
		
		System.out.println("encoder: "+encoder);
		
		byte[] decode = org.apache.commons.codec.binary.Base64.decodeBase64(encoder.getBytes());
		
		System.out.println("decode: " + new String(decode));
	}
	
	public static void bouncyCastle64() {
		byte[] encode = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());

		System.out.println("encoder: "+new String(encode));
		
		byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
		
		System.out.println("decode: " + new String(decode));
		
	}
}
