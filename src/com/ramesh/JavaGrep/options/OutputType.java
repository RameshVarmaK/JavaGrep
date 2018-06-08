package com.ramesh.JavaGrep.options;

import java.util.Optional;

public enum OutputType {
    LIST_COUNT("-lc"), LIST("-l");
    private String value;

    OutputType(String value) {
        this.value = value;
    }

    public static Optional<OutputType> get(String value) {
        Optional<OutputType> outputType = Optional.empty();
        OutputType[] values = OutputType.values();
        for (OutputType type : values) {
            if (value.equals(type.value)) {
                outputType = Optional.of(type);
                break;
            }
        }
        return outputType;
    }
}