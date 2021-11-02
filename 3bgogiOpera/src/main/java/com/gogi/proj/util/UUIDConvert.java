package com.gogi.proj.util;

public class UUIDConvert {

	

	/**
	 * 
	 * @MethodName : longToBase64
	 * @date : 2021. 10. 12.
	 * @author : Jeon KiChan
	 * @param v
	 * @return
	 * @메소드설명 : 64비트로 전환하여 고유키값으로 만들기
	 */
	public static String longToBase64(long v) {

		final char[] digits = { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', // 1
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', // 2
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', // 3
				'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', // 4
				'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', // 5
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', // 6
				'Y', 'Z', '#', '$' };							  // 7
		
		int shift = 6;
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << shift;
		long mask = radix - 1;
		long number = v;

		do {
			buf[--charPos] = digits[(int) (number & mask)];
			number >>>= shift;
		
		}while (number != 0);

		return new String(buf, charPos, (64 - charPos));
	}

}
