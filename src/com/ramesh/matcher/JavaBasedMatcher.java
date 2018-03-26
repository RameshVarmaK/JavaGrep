package com.ramesh.matcher;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * String matcher to check weather text is equal to pattern or not
 * 
 * @author rameshv
 *
 */
public class JavaBasedMatcher implements StringMatcher {
	private Pattern pattern;

	public JavaBasedMatcher(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	@Override
	public boolean isMatched(String text) {
		Matcher m = pattern.matcher(text);
		return m.matches();
	}

}
