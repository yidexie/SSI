package com.weixin.core.util;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Des {
	public static String encryptDES(String encryptString, String encryptKey)
			throws Exception {
		if (encryptString.trim().equals("")) {
			return encryptString;
		}

		byte[] iv = encryptKey.getBytes();
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

		String str = bytesToHexString(encryptedData);
		return str;
	}

	public static String decryptDES(String decryptString, String decryptKey) {

		byte[] iv = decryptKey.getBytes();
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");

		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte decryptedData[] = cipher.doFinal(hexToBytes(decryptString));
			return new String(decryptedData);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}

	}

	/**
	 * Convert byte[] to hex string.
	 * 
	 * @param src
	 *            byte[] data
	 * 
	 * @return hex string
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] hexToBytes(String hexString) {
		char[] hex = hexString.toCharArray();
		// 轉rawData長度減半
		int length = hex.length / 2;
		byte[] rawData = new byte[length];
		for (int i = 0; i < length; i++) {
			// 先將hex資料轉10進位數值
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			// 將第一個值的二進位值左平移4位,ex: 00001000 => 10000000 (8=>128)
			// 然後與第二個值的二進位值作聯集ex: 10000000 | 00001100 => 10001100 (137)
			int value = (high << 4) | low;
			// 與FFFFFFFF作補集
			if (value > 127)
				value -= 256;
			// 最後轉回byte就OK
			rawData[i] = (byte) value;
		}
		return rawData;
	}

}
