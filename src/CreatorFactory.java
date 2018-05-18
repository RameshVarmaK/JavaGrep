import com.ramesh.matcher.JavaBasedMatcher;
import com.ramesh.matcher.custom.AutomataBasedMatcher;
import com.ramesh.matcher.custom.StringMatcher;

public class CreatorFactory {
    public StringMatcher create(Configuration configuration) {
        if (configuration.getMatcherType().equals("-c")) {
            return new AutomataBasedMatcher(configuration.getPattern());
        } else if (configuration.getMatcherType().equals("-j")) {
            return new JavaBasedMatcher(configuration.getPattern());
        }
        return null;
    }
}