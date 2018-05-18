import com.ramesh.matcher.custom.DirectoryProcessor;
import com.ramesh.matcher.custom.FileProcessor;
import com.ramesh.matcher.custom.StringMatcher;

import java.io.IOException;

public enum InputFormat {
    FILE("-f") {
        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException {
            FileProcessor file=new FileProcessor();
            file.process(compiledPattern,configuration.getInputSource());
        }
    },DIRECTORY("-d") {
        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException {
            DirectoryProcessor directory=new DirectoryProcessor();
            directory.process(compiledPattern,configuration.getInputSource());
        }
    },TEXT("-t") {
        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration) {
            System.out.println(compiledPattern.isMatched(configuration.getInputSource()));
        }
    };
    private String symbol;
    InputFormat(String symbol){
        this.symbol=symbol;
    }
    public abstract void compare(StringMatcher compiledPattern,Configuration configuration) throws IOException;
    public static InputFormat getNameForValue(Object value) {
        InputFormat[] values = InputFormat.values();
        for (InputFormat inputFormat : values) {
            if (value.equals(inputFormat.symbol)) {
                return inputFormat;
            }
        }
        return null;
    }
}
