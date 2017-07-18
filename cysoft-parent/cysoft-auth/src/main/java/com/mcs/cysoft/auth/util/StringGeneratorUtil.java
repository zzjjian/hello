package com.mcs.cysoft.auth.util;

import java.security.SecureRandom;

public class StringGeneratorUtil {
	
	private static final char[] PRINTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345679"
	        .toCharArray();
	
	protected static final int DEFAULT_MAX_RANDOM_LENGTH = 35;

	public static String getString() {
		SecureRandom randomizer = new SecureRandom();
		
		final byte[] random = new byte[DEFAULT_MAX_RANDOM_LENGTH];
		randomizer.nextBytes(random);
		
		return convertBytesToString(random);
		
	}
	
	private static String convertBytesToString(final byte[] random) {
        final char[] output = new char[random.length];
        for (int i = 0; i < random.length; i++) {
            final int index = Math.abs(random[i] % PRINTABLE_CHARACTERS.length);
            output[i] = PRINTABLE_CHARACTERS[index];
        }

        return new String(output);
    }

}
