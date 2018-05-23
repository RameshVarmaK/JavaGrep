import com.ramesh.matcher.custom.StringMatcher;

import java.io.IOException;

public interface InputComparator {
    void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException;
}
