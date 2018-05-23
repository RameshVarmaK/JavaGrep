import com.ramesh.matcher.custom.StringMatcher;

import java.io.*;

public enum InputSource implements Processor {
    FILE("-f") {
        @Override
        public void onSource(StringMatcher compiledPattern, Configuration configuration, BufferedWriter bufferedWriter) throws IOException {
            configuration.getOutputType().FileProcessor(compiledPattern,configuration.getInputSourceData(),bufferedWriter);
        }

        @Override
        public void onFinish(BufferedWriter bufferedWriter) throws IOException {
            bufferedWriter.flush();
        }

        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException {
            BufferedWriter bufferedWriter = configuration.getOutputSource().print(configuration);
            onSource(compiledPattern, configuration, bufferedWriter);
            onFinish(bufferedWriter);
        }
    }, DIRECTORY("-d") {
        @Override
        public void onSource(StringMatcher compiledPattern, Configuration configuration, BufferedWriter bufferedWriter) throws IOException {
            File folder = new File(configuration.getInputSourceData());
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    bufferedWriter.write(listOfFile.getName());
                    bufferedWriter.newLine();
                    configuration.getOutputType().FileProcessor(compiledPattern,listOfFile.toString(),bufferedWriter);
                }
            }
        }

        @Override
        public void onFinish(BufferedWriter bufferedWriter) throws IOException {
            bufferedWriter.flush();
        }

        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException {
            BufferedWriter bufferedWriter = configuration.getOutputSource().print(configuration);
            onSource(compiledPattern, configuration, bufferedWriter);
            onFinish(bufferedWriter);

        }
    }, TEXT("-t") {
        @Override
        public void onSource(StringMatcher compiledPattern, Configuration configuration, BufferedWriter bufferedWriter) throws IOException {
            if (compiledPattern.isMatched((configuration.getInputSourceData()))) {
                if (configuration.getOutputType() == OutputType.LIST_COUNT) {
                    bufferedWriter.write(Integer.toString(1));
                } else {
                    bufferedWriter.write(configuration.getInputSourceData());
                    bufferedWriter.newLine();
                }
            }
        }

        @Override
        public void onFinish(BufferedWriter bufferedWriter) throws IOException {
            bufferedWriter.flush();
        }

        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException {
            BufferedWriter bufferedWriter = configuration.getOutputSource().print(configuration);
            onSource(compiledPattern, configuration, bufferedWriter);
            onFinish(bufferedWriter);
        }
    };
    private String symbol;

    InputSource(String symbol) {
        this.symbol = symbol;
    }

    public static InputSource getNameForValue(Object value) {
        InputSource[] values = InputSource.values();
        for (InputSource inputSource : values) {
            if (value.equals(inputSource.symbol)) {
                return inputSource;
            }
        }
        return null;
    }

    abstract public void compare(StringMatcher compiledPattern, Configuration configuration) throws IOException;
}
