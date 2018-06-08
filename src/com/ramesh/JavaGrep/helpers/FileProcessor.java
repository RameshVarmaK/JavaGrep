package com.ramesh.JavaGrep.helpers;

import com.ramesh.JavaGrep.matcher.StringMatcher;
import com.ramesh.JavaGrep.options.OutputWriter;
import com.ramesh.JavaGrep.options.WriterFactory;
import com.ramesh.JavaGrep.threads.ComparatorThread;
import com.ramesh.JavaGrep.threads.ReaderThread;
import com.ramesh.JavaGrep.utils.ActionPerformerFactory;
import com.ramesh.JavaGrep.utils.Configuration;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessor {

    private final Queue<String> dataQueue;
    private final StringMatcher matcher;
    private final WriterFactory writerFactory;
    private final Configuration configuration;
    private AtomicBoolean isCompleted;
    private AtomicBoolean compilationFlag;

    public FileProcessor(StringMatcher matcher, WriterFactory writerFactory, Configuration configuration) {
//        this.dataQueue = new LinkedBlockingQueue<>();
        this.matcher = matcher;
        this.writerFactory = writerFactory;
        this.configuration = configuration;

        this.dataQueue = new ConcurrentLinkedQueue<>();
        this.isCompleted = new AtomicBoolean();

        this.compilationFlag = new AtomicBoolean();
    }

    public void process(File file) throws IOException, WriterException, InterruptedException {
        FileInputStream inputFile = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));

        ReaderThread readerThread = new ReaderThread(reader, dataQueue, isCompleted);
        readerThread.start();

        OutputWriter writer = writerFactory.createWriter(file.getName());
        ActionPerformer performer = ActionPerformerFactory.create(configuration, writer);

        ComparatorThread comparatorThread = null;
        for (int i = 0; i < configuration.getWriterThreadsSize(); i++) {
            comparatorThread = new ComparatorThread(dataQueue, isCompleted, matcher, performer);
            comparatorThread.start();
        }
        comparatorThread.join();

        compilationFlag.set(true);
        Logger logger = Logger.getLogger("com.ramesh.JavaGrep.MatcherApp");
        logger.log(Level.INFO, "compiler thread end point" + String.valueOf(System.currentTimeMillis()));
        performer.onFinish();
    }

}