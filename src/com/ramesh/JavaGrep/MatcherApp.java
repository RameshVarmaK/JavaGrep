package com.ramesh.JavaGrep;

import com.ramesh.JavaGrep.matcher.StringMatcher;
import com.ramesh.JavaGrep.options.WriterFactory;
import com.ramesh.JavaGrep.utils.AbstractWriterFactory;
import com.ramesh.JavaGrep.utils.Configuration;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatcherApp {

    public void match(Configuration configuration) throws Exception {
        long start = System.currentTimeMillis();
        Logger logger = Logger.getLogger("com.ramesh.JavaGrep.MatcherApp");
        logger.setUseParentHandlers(false);
        logger.addHandler(new FileHandler("logdump.xml", false));
        logger.setLevel(Level.ALL);
        logger.log(Level.INFO, "matcher ap start point " + String.valueOf(start));
        // todo
//        configuration.isValid();

        StringMatcher matcher = configuration.getMatcherType().createMatcher(configuration.getPattern());

        try (WriterFactory writerFactory = AbstractWriterFactory.createWriter(configuration)) {
            configuration.getInputSource().compare(matcher, configuration, writerFactory);
        }

        long end = System.currentTimeMillis();
        logger.log(Level.INFO, "matcher app end point " + String.valueOf(end));
        logger.log(Level.INFO, "Time taken : " + (end - start) + "ms");


    }

}