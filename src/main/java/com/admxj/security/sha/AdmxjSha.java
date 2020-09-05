package com.admxj.security.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * @author 项金
 */
public class AdmxjSha {
	
	private static String src = "security sha";
	
	public static void main(String[] args) throws Exception {
		System.out.println("======= jdkSHA1 =======");
		AdmxjSha.jdkSha1();
	}
	
	public static void jdkSha1() throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		mDigest.update(src.getBytes());
		String sha = Hex.encodeHexString(mDigest.digest());
		System.out.println("sha: " + sha);
		System.out.println("result: "+"0b5fc48501d9c8ab5e5196354d00f7bb57a73d98".equals(sha));
	}
}
