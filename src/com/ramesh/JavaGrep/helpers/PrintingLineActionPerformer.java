package com.ramesh.JavaGrep.helpers;

import com.ramesh.JavaGrep.options.OutputWriter;

import java.io.IOException;

public class PrintingLineActionPerformer implements ActionPerformer {

    private final OutputWriter writer;

    public PrintingLineActionPerformer(OutputWriter writer) {
        this.writer = writer;
    }

    @Override
    public void onMatch(String matchedText) throws IOException {
        writer.print(matchedText);
    }

    @Override
    public void onFinish() throws IOException, WriterException {

    }
}
