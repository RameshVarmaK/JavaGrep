package com.ramesh.JavaGrep.helpers;

import java.util.logging.Logger;

public class WriterException extends Exception {
    private String message;

    public WriterException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        Logger logger=Logger.getLogger("com.ramesh.JavaGrep.MatcherApp");
        logger.warning(message);
        return ("Raised the custom exception " + " '' " + message + " '' ");
    }
}