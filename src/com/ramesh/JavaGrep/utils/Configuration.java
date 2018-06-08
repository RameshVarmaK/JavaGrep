package com.ramesh.JavaGrep.utils;

import com.ramesh.JavaGrep.options.InputSource;
import com.ramesh.JavaGrep.options.MatcherType;
import com.ramesh.JavaGrep.options.OutputSourceType;
import com.ramesh.JavaGrep.options.OutputType;

public class Configuration {
    private String pattern;
    private MatcherType matcherType;
    private InputSource inputSource;
    private String inputSourceData;
    private OutputSourceType outputSourceType;
    private String outputSourceData;
    private OutputType outputType;
    private int writerThreadsSize;

    public Configuration(ConfigurationBuilder builder) {
        this.pattern = builder.pattern;
        this.matcherType = builder.matcherType;
        this.inputSource = builder.inputSource;
        this.inputSourceData = builder.inputSourceData;
        this.outputSourceType = builder.outputSourceType;
        this.outputSourceData = builder.outputSourceData;
        this.outputType = builder.outputType;
        this.writerThreadsSize = builder.writerThreadsSize;
    }

    public OutputSourceType getOutputSourceType() {
        return outputSourceType;
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public String getOutputSourceData() {
        return outputSourceData;
    }

    public String getPattern() {
        return pattern;
    }

    public MatcherType getMatcherType() {
        return matcherType;
    }

    public InputSource getInputSource() {
        return inputSource;
    }

    public String getInputSourceData() {
        return inputSourceData;
    }

    public int getWriterThreadsSize() {
        return writerThreadsSize;
    }

}