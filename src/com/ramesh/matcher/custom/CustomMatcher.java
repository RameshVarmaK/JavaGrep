package com.ramesh.matcher.custom;

import com.ramesh.matcher.RegexMatcher;
import com.ramesh.matcher.custom.StringMatcher;

/**
 * Custom implementation for string matcher
 * 
 * @author rameshv
 *
 */
public class CustomMatcher extends RegexMatcher implements StringMatcher {
	private String pattern;

	public CustomMatcher(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean isMatched(String mainText) {
		text=mainText.toCharArray();
		constructList(pattern.toCharArray());
		char[] curText = null;
		char curOp;
		int canRead = 0;
		while (listPosition.get() < operators.size()) {
			if (canRead == 0) {
				curText = data.remove().toCharArray();
			}
			curOp = operators.get(listPosition.get());
			listPosition.incrementAndGet();
			canRead = 0;
			if (curOp == '(') {
				canRead = 1;
				if (matchForBrace(curText) == 0) {
					return false;
				}
			} else if (curOp == '+') {

				if (matchForPlus(curText) == 0) {
					return false;
				}

			} else if (curOp == '*') {

				if (matchForStar(curText) == 0) {
					return false;
				}

			} else if (curOp == '?') {

				if (matchForQues(curText) == 0) {
					return false;
				}
			} else if (curOp == '.') {
				if (matchForDot(curText) == 0) {
					return false;
				}
			}
		}
		if (textPosition.get() != text.length) {
			return false;
		}
		System.out.println(operators.toString());
		return true;
	}

}
