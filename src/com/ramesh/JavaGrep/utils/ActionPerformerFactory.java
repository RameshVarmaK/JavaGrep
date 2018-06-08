package com.ramesh.JavaGrep.utils;

import com.ramesh.JavaGrep.helpers.ActionPerformer;
import com.ramesh.JavaGrep.helpers.CountActionPerformer;
import com.ramesh.JavaGrep.helpers.PrintingLineActionPerformer;
import com.ramesh.JavaGrep.options.OutputType;
import com.ramesh.JavaGrep.options.OutputWriter;

public class ActionPerformerFactory {

    public static ActionPerformer create(Configuration configuration, OutputWriter writer) {
        ActionPerformer performer = null;
        if (configuration.getOutputType() == OutputType.LIST) {
            performer = new PrintingLineActionPerformer(writer);
        } else if (configuration.getOutputType() == OutputType.LIST_COUNT) {
            performer = new CountActionPerformer(writer);
        }
        return performer;
    }
}
