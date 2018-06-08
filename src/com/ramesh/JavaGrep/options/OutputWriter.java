package com.ramesh.JavaGrep.options;

import java.io.IOException;

public interface OutputWriter {

    void print(String text) throws IOException;
}
