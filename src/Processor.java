import com.ramesh.matcher.custom.StringMatcher;

import java.io.BufferedWriter;
import java.io.IOException;

public interface Processor {
    void onSource(StringMatcher compiledPattern, Configuration configuration, BufferedWriter bufferedWriter) throws IOException;

    void onFinish(BufferedWriter bufferedWriter) throws IOException;
}
