package com.ramesh.JavaGrep.options;

import com.ramesh.JavaGrep.matcher.JavaBasedMatcher;
import com.ramesh.JavaGrep.matcher.StringMatcher;
import com.ramesh.JavaGrep.matcher.custom.AutomataBasedMatcher;

import java.util.Optional;

public enum MatcherType {
    JAVA("-j") {
        @Override
        public StringMatcher createMatcher(String pattern) {
            return new JavaBasedMatcher(pattern);
        }
    }, CUSTOM("-c") {
        @Override
        public StringMatcher createMatcher(String pattern) {
            return new AutomataBasedMatcher(pattern);
        }
    };
    private String symbol;

    MatcherType(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<MatcherType> valueFor(String value) {
        Optional<MatcherType> optionalMatcher = Optional.empty();
        MatcherType[] values = MatcherType.values();
        for (MatcherType matcherType : values) {
            if (value.equals(matcherType.symbol)) {
                optionalMatcher = Optional.of(matcherType);
                break;
            }
        }
        return optionalMatcher;
    }

    public abstract StringMatcher createMatcher(String pattern);
}