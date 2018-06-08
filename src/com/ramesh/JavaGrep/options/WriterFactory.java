package com.ramesh.JavaGrep.options;

import java.io.Writer;

public class WriterFactory implements AutoCloseable {

    private final Writer writer;

    public WriterFactory(Writer writer) {
        this.writer = writer;
    }

    public OutputWriter createWriter(String prefix) {
        return new PrefixAppendingWriter(prefix, writer);
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}