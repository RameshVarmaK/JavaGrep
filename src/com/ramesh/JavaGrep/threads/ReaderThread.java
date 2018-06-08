package com.ramesh.JavaGrep.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderThread extends Thread {
    private BufferedReader reader;
    private Queue<String> dataQueue;
    private AtomicBoolean isCompleted;

    public ReaderThread(BufferedReader reader, Queue<String> dataQueue, AtomicBoolean isCompleted) {
        this.reader = reader;
        this.dataQueue = dataQueue;
        this.isCompleted = isCompleted;
    }

    @Override
    public void run() {
        try {
            String text;
            while ((text = reader.readLine()) != null) {
                dataQueue.add(text);
//                System.out.println("added");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            Logger logger = Logger.getLogger("com.ramesh.JavaGrep.MatcherApp");
            logger.log(Level.INFO, "reader thread completed at " + String.valueOf(System.currentTimeMillis() +" datasize " + dataQueue.size()));
            isCompleted.set(true);
        }
    }
}