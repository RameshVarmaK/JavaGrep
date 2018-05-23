import com.ramesh.matcher.custom.StringMatcher;

import java.io.IOException;

public class TextFormat implements InputComparator {
    @Override
    public void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException {
        System.out.println(compiledPattern.isMatched(configuration.getInputSourceData()));
    }
}
