package com.mcs.cysoft.auth.util;

public class TokenGeneratorUtil {

	private static String PREFIX = "cysoft";
	private static String SUFFIX = "cysoft01.com.cn";

	public static String getToken() {

		StringBuffer buffer = new StringBuffer();
		buffer.append(PREFIX);
		buffer.append('-');
		buffer.append(NumberGeneratorUtil.getNumber());
		buffer.append(System.currentTimeMillis());
		buffer.append('-');
		buffer.append(StringGeneratorUtil.getString());
		buffer.append('-');
		buffer.append(SUFFIX);

		return buffer.toString();
	}
}
