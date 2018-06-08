package com.ramesh.JavaGrep.options;

import java.io.IOException;
import java.io.Writer;

public class PrefixAppendingWriter extends OutputWriterImpl {

    private final String prefix;

    public PrefixAppendingWriter(String prefix, Writer writer) {
        super(writer);
        this.prefix = prefix;
    }

    @Override
    public void print(String text) throws IOException {
        super.print(prefix + ":" + text + "\n");
    }
}
