import com.ramesh.matcher.JavaBasedMatcher;
import com.ramesh.matcher.custom.AutomataBasedMatcher;
import com.ramesh.matcher.custom.StringMatcher;

public enum Matcher {
    JAVA("-j") {
        @Override
        public StringMatcher getMatcher(Configuration configuration) {
            return new JavaBasedMatcher(configuration.getPattern());
        }
    }, CUSTOM("-c") {
        @Override
        public StringMatcher getMatcher(Configuration configuration) {
            return new AutomataBasedMatcher(configuration.getPattern());
        }
    };
    private String symbol;

    Matcher(String symbol) {
        this.symbol = symbol;
    }

    public static Matcher getNameForValue(String value) {
        Matcher[] values = Matcher.values();
        for (Matcher matcher : values) {
            if (value.equals(matcher.symbol)) {
                return matcher;
            }
        }
        return null;
    }

    public abstract StringMatcher getMatcher(Configuration configuration);
}
