package com.calf.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtils {
	/**
	 * MD5算法,来源于JForum
	 * @ption
	 */
	public static String md5(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}

		StringBuilder hexString = new StringBuilder();

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();

			for (byte element : hash) {
				if ((0xff & element) < 0x10) {
					hexString.append('0').append(
							Integer.toHexString((0xFF & element)));
				} else {
					hexString.append(Integer.toHexString(0xFF & element));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

		return hexString.toString();
	}
}
