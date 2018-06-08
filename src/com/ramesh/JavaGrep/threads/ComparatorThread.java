package com.ramesh.JavaGrep.threads;

import com.ramesh.JavaGrep.helpers.ActionPerformer;
import com.ramesh.JavaGrep.matcher.StringMatcher;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComparatorThread extends Thread {
    private Queue<String> dataQueue;
    private AtomicBoolean isCompleted;
    private StringMatcher matcher;
    private ActionPerformer performer;
    public ComparatorThread(Queue<String> dataQueue, AtomicBoolean isCompleted,StringMatcher matcher,ActionPerformer performer) {
        this.dataQueue = dataQueue;
        this.isCompleted = isCompleted;
        this.matcher=matcher;
        this.performer=performer;
    }

    @Override
    public void run() {
        while (true) {
            String textToMatch = dataQueue.poll();

            if (textToMatch == null && isCompleted.get()) {
                break;
            } else {
                if (textToMatch != null) {
                    if (matcher.isMatched(textToMatch)) {
                        try {
                            performer.onMatch(textToMatch);
                        } catch (IOException e) {
                            throw new RuntimeException("Error while writing result", e);
                        }
                    }
                }
            }
        }
    }
}