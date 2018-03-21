package com.wjb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String getMd5(String input_text) {
		// ���md5�����㷨
		if (input_text != null && input_text.length() > 16) {
			return input_text;
		}
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �����ַ���
		digest.update(input_text.getBytes());
		// ��ü��ܽ��
		byte[] bytes = digest.digest();
		StringBuilder builder = new StringBuilder();
		// �����ܽ��ת��Ϊ16�����ַ���
		for (int i = 0; i < bytes.length; i++) {
			String s = Integer.toHexString(Math.abs(bytes[i]));
			builder.append(s);
		}
		return builder.toString();
	}

	public static void main(String args[]) {
		MD5Util md5 = new MD5Util();
		System.out.println(md5.getMd5("abc"));
	}
}
