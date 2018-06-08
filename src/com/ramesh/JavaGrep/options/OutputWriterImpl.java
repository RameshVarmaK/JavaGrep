package com.ramesh.JavaGrep.options;

import java.io.IOException;
import java.io.Writer;

public class OutputWriterImpl implements OutputWriter {

    private final Writer writer;

    public OutputWriterImpl(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void print(String text) throws IOException {
        writer.write(text);
    }
}
