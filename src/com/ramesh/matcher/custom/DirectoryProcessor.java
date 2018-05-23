package com.ramesh.matcher.custom;

import java.io.File;
import java.io.IOException;

public class DirectoryProcessor {

    public void process(String directory) throws IOException {
        File folder=new File(directory);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println(listOfFile.getName());
            }
        }
    }
}
