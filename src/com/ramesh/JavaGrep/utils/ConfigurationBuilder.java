package com.ramesh.JavaGrep.utils;

import com.ramesh.JavaGrep.helpers.WriterException;
import com.ramesh.JavaGrep.options.InputSource;
import com.ramesh.JavaGrep.options.MatcherType;
import com.ramesh.JavaGrep.options.OutputSourceType;
import com.ramesh.JavaGrep.options.OutputType;

public class ConfigurationBuilder {
    public String pattern;
    public MatcherType matcherType;
    public InputSource inputSource;
    public String inputSourceData;
    public OutputSourceType outputSourceType;
    public String outputSourceData;
    public OutputType outputType;
    public int writerThreadsSize = 21;

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setMatcherType(MatcherType matcherType) {
        this.matcherType = matcherType;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public void setInputSourceData(String inputSourceData) {
        this.inputSourceData = inputSourceData;
    }

    public void setOutputSourceType(OutputSourceType outputSourceType) {
        this.outputSourceType = outputSourceType;
    }

    public void setOutputSourceData(String outputSourceData) {
        this.outputSourceData = outputSourceData;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public void setWriterThreadsSize(int writerThreadsSize) {
        this.writerThreadsSize = writerThreadsSize;
    }


    public Configuration buid() throws WriterException {
        if (this.pattern == null || this.matcherType == null || this.inputSource == null || this.inputSourceData == null ||
                this.outputSourceType == null || this.outputType == null) {
            throw new WriterException("InSufficient data provided to process");
        }
        if (this.outputSourceType == OutputSourceType.FILE) {
            if (this.outputSourceData == null) {
                throw new WriterException("Output file name was missing");
            }
        }
        return new Configuration(this);
    }

}
