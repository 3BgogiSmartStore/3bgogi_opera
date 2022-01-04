package com.gogi.proj.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	public String mysqlSafe(String input) {
		if (input == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if (i < (input.length() - 1)) {
				if (Character.isSurrogatePair(input.charAt(i), input.charAt(i + 1))) {
					i += 1;
					continue;
				}
			}
			sb.append(input.charAt(i));
		}
		return sb.toString();
	}

}
