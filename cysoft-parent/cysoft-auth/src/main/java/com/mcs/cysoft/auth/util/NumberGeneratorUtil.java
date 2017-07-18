package com.mcs.cysoft.auth.util;

import java.util.Random;

public class NumberGeneratorUtil {

	public static String getNumber() {
		
		Random random = new Random();
		
		StringBuffer numberBuffer = new StringBuffer();
		
		for (int i = 0; i < 6; i++) {
			numberBuffer.append(random.nextInt(10));
		}
		
		return numberBuffer.toString();
	}

}
