package com.ramesh.JavaGrep.options;


import java.util.Optional;

public enum OutputSourceType {
    FILE("-of"),
    CONSOLE("-oc");

    private String value;

    OutputSourceType(String value) {
        this.value = value;
    }

    public static Optional<OutputSourceType> valueFor(String value) {
        Optional<OutputSourceType> outputSource = Optional.empty();
        OutputSourceType[] values = OutputSourceType.values();
        for (OutputSourceType source : values) {
            if (value.equals(source.value)) {
                outputSource = Optional.of(source);
                break;
            }
        }
        return outputSource;
    }
}
