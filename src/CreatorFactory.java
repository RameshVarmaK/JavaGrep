import com.ramesh.matcher.custom.StringMatcher;

public class CreatorFactory {
    public StringMatcher create(Configuration configuration) {
        return configuration.getMatcherType().getMatcher(configuration);
    }
}