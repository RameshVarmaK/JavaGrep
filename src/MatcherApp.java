import com.ramesh.matcher.custom.StringMatcher;

import java.io.IOException;

public class MatcherApp {

    public void matcher(Configuration configuration) throws IOException {
        long start = System.currentTimeMillis();
        CreatorFactory creatorFactory = new CreatorFactory();
        StringMatcher compiledPattern = creatorFactory.create(configuration);
        configuration.getInputSource().compare(compiledPattern, configuration);
        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start) + "ms");
    }

}