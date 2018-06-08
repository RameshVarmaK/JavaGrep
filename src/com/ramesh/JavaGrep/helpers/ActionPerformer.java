package com.ramesh.JavaGrep.helpers;

import java.io.IOException;

public interface ActionPerformer {

    void onMatch(String matchedText) throws IOException;

    void onFinish() throws IOException, WriterException;
}