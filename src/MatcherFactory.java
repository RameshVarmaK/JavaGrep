import com.ramesh.matcher.custom.DirectoryProcessor;
import com.ramesh.matcher.custom.StringMatcher;
import com.ramesh.matcher.custom.FileProcessor;

import java.io.IOException;

public class MatcherFactory {

    public void choose(StringMatcher compiledPattern,Configuration configuration) throws IOException {
        int position;
        if(configuration.getInputType().equals("-t")){
            System.out.println(compiledPattern.isMatched(configuration.getInputSource()));
        }else if(configuration.getInputType().equals("-f")){
            FileProcessor file=new FileProcessor();
            file.process(compiledPattern,configuration.getInputSource());
        }else if(configuration.getInputType().equals("-d")){
            DirectoryProcessor directory=new DirectoryProcessor();
            directory.process(compiledPattern,configuration.getInputSource());
        }
    }
}
