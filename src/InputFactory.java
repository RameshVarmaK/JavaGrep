import com.ramesh.matcher.custom.StringMatcher;

import java.io.IOException;

public class InputFactory {

    public void choose(StringMatcher compiledPattern, Configuration configuration) throws IOException {

        configuration.getInputSource().compare(compiledPattern, configuration);
    }
}