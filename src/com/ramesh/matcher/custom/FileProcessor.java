package com.ramesh.matcher.custom;

import java.io.*;

public class FileProcessor {
    public void process(StringMatcher matcher,String fileName) throws IOException {
        FileInputStream inputFile = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));
        String text;
        while ((text = reader.readLine()) != null) {
            if (matcher.isMatched(text)) {
                System.out.println(text);
            }
        }
    }
}