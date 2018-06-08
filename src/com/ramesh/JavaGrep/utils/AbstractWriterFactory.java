package com.ramesh.JavaGrep.utils;

import com.ramesh.JavaGrep.options.OutputSourceType;
import com.ramesh.JavaGrep.options.WriterFactory;

import java.io.*;

public class AbstractWriterFactory {

    public static WriterFactory createWriter(Configuration configuration) throws IOException {

        Writer writer = null;
        if (configuration.getOutputSourceType() == OutputSourceType.FILE) {
            writer = new FileWriter(new File(configuration.getOutputSourceData()));
        } else if (configuration.getOutputSourceType() == OutputSourceType.CONSOLE) {
            writer = new OutputStreamWriter(System.out);
        }

        return new WriterFactory(writer);
    }
}
