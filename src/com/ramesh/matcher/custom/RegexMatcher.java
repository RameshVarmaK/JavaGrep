package com.ramesh.matcher;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class RegexMatcher {

	public LinkedList<String> data = new LinkedList<String>();
	public LinkedList<Character> operators = new LinkedList<Character>();
	public AtomicInteger textPosition = new AtomicInteger(0);
	public AtomicInteger listPosition = new AtomicInteger(0);
	public char[] text;

	/**
	 * Checks weather the character is a-z or A-Z
	 * 
	 * @param character
	 * @return true if its in a-z or A-Z or else false
	 */
	public boolean isLetter(char c) {
		if ((int) c >= 65 && (int) c <= 122) {
			return true;
		}
		return false;
	}

	/**
	 * Constructing the stack with the given text and pattern
	 * 
	 * @param inputText
	 * @param inputPattern
	 * @param dataQueue
	 * @param operatorsQueue
	 */
	public void constructList(char[] pattern) {
		for (int i = 0; i < pattern.length; i++) {
			if (isLetter(pattern[i])) {
				// move till the operator and make a bunch
				String subStr = "";
				subStr += pattern[i];
				while (isLetter(pattern[i + 1])) {
					subStr += pattern[i + 1];
					i++;
				}
				data.add(subStr);
			} else {
				operators.add(pattern[i]);
			}
		}
		System.out.println("data is " + data.toString());
		System.out.println("operators " + operators.toString());
	}

	/**
	 * matches for (
	 * 
	 * @param currentText
	 * @param mainText
	 * @param currentPosition
	 * @param operatorsQueue
	 * @return 1 if success or else 0
	 */
	public int matchForBrace(char[] curText) {
		if (operators.get(listPosition.get()) != ')') {
			return 0;
		}
		for (int j = 0; j < curText.length; j++) {
			if (curText[j] != text[textPosition.get()]) {
				return 0;
			}
			textPosition.getAndIncrement();

		}
		listPosition.getAndIncrement();
		return 1;
	}

	/**
	 * matches for +, once or more check with the prev text and decide to move
	 * forward or to read the next string
	 * 
	 * @param curText
	 * @param mainText
	 * @param currentposition
	 * @return 1 if success or else 0
	 */
	public int matchForPlus(char[] curText) {
		while (textPosition.get() < text.length && curText[0] == text[textPosition.get()]) {

			for (int j = 0; j < curText.length; j++) {
				if (curText[j] != text[textPosition.get()]) {
					return 0;
				}
				textPosition.getAndIncrement();
			}
		}
		return 1;
	}

	/**
	 * matches for *, zero or more check the match till the last character handle
	 * the last character
	 * 
	 * @param curText
	 * @param mainText
	 * @param currentPosition
	 * @return 1 if success or else 0
	 */
	public int matchForStar(char[] curText) {
		// check previous for brace (
		for (int j = 0; j < curText.length - 1; j++) {
			if (curText[j] != text[textPosition.get()]) {
				return 0;
			}
			textPosition.getAndIncrement();
		}
		while (textPosition.get() < text.length && curText[curText.length - 1] == text[textPosition.get()]) {
			textPosition.getAndIncrement();
		}
		return 1;
	}

	/**
	 * matches for ?,once or not at all check the match till the last character
	 * handle the last character
	 * 
	 * @param curText
	 * @param mainText
	 * @param currentPosition
	 * @return 1 if success or else 0
	 */
	public int matchForQues(char[] curText) {
		for (int j = 0; j < curText.length - 1; j++) {
			if (curText[j] != text[textPosition.get()]) {
				return 0;
			}
			textPosition.getAndIncrement();
		}
		if (textPosition.get() + 1 != text.length) {
			int count = 0;
			while (textPosition.get() < text.length && text[textPosition.get()] == curText[curText.length - 1]) {
				count++;
				textPosition.getAndIncrement();
			}
			if (count >= 2) {
				return 0;
			}

		} else {
			textPosition.getAndIncrement();
		}
		return 1;
	}

	/**
	 * matches with ., match with any character
	 * 
	 * @param curText
	 * @param mainText
	 * @param currentPosition
	 * @return 1 if success or else 0
	 */
	public int matchForDot(char[] curText) {
		if (textPosition.get() == text.length) {
			return 0;
		}
		for (int j = 0; j < curText.length; j++) {
			if (curText[j] != text[textPosition.get()]) {
				return 0;
			}
			textPosition.getAndIncrement();
		}

		textPosition.getAndIncrement();
		return 1;
	}
}