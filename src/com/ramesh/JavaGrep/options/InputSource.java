package com.ramesh.JavaGrep.options;

import com.ramesh.JavaGrep.helpers.FileProcessor;
import com.ramesh.JavaGrep.helpers.WriterException;
import com.ramesh.JavaGrep.matcher.StringMatcher;
import com.ramesh.JavaGrep.utils.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public enum InputSource {
    FILE("-f") {
        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration, WriterFactory outputWriter) throws IOException, WriterException, InterruptedException {
            FileProcessor fileProcessor = new FileProcessor(compiledPattern, outputWriter, configuration);
            fileProcessor.process(new File(configuration.getInputSourceData()));
        }
    }, DIRECTORY("-d") {
        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration, WriterFactory outputWriter) throws IOException, WriterException, InterruptedException {
            File folder = new File(configuration.getInputSourceData());
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    FileProcessor fileProcessor = new FileProcessor(compiledPattern, outputWriter, configuration);
                    fileProcessor.process(listOfFile);
                }
            }
        }
    }, TEXT("-t") {
        @Override
        public void compare(StringMatcher compiledPattern, Configuration configuration, WriterFactory outputWriter) throws IOException {
            if (compiledPattern.isMatched((configuration.getInputSourceData()))) {
                if (configuration.getOutputType() == OutputType.LIST_COUNT) {
                    outputWriter.createWriter("text").print(Integer.toString(1));
                } else {
                    outputWriter.createWriter("text").print(configuration.getInputSourceData());
                }
            }
        }
    };
    private String symbol;

    InputSource(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<InputSource> getNameForValue(Object value) {
        Optional<InputSource> optionalInputSource = Optional.empty();
        InputSource[] values = InputSource.values();
        for (InputSource inputSource : values) {
            if (value.equals(inputSource.symbol)) {
                optionalInputSource = Optional.of(inputSource);
                break;
            }
        }
        return optionalInputSource;
    }

    abstract public void compare(StringMatcher compiledPattern, Configuration configuration, WriterFactory bufferedWriter) throws IOException, InterruptedException, WriterException;
}