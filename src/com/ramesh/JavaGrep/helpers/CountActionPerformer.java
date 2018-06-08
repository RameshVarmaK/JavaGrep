package com.ramesh.JavaGrep.helpers;

import com.ramesh.JavaGrep.options.OutputWriter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class CountActionPerformer implements ActionPerformer {

    private final OutputWriter writer;
    private AtomicInteger count;

    public CountActionPerformer(OutputWriter writer) {
        this.count=new AtomicInteger();
        this.writer = writer;
    }

    @Override
    public synchronized void onMatch(String matchedText) {
        count.getAndIncrement();
    }

    @Override
    public void onFinish() throws IOException {
        writer.print(String.valueOf(count.get()));
    }
}
