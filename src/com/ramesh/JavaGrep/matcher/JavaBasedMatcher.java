package com.ramesh.JavaGrep.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String match to check weather text is equal to pattern or not
 *
 * @author rameshv
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
