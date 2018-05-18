import com.ramesh.matcher.custom.StringMatcher;

import java.io.IOException;

public class MatcherApp {

    public static void matcher(Configuration configuration) throws IOException {
        StringMatcher compiledPattern = configuration.getMatcherType().getMatcher(configuration);
        configuration.getInputType().compare(compiledPattern, configuration);
    }

}