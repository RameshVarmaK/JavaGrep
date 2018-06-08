package com.ramesh.JavaGrep.matcher.custom;

import com.ramesh.JavaGrep.matcher.StringMatcher;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class AutomataBasedMatcher implements StringMatcher {
    private Node headNode;

    public AutomataBasedMatcher(String inputPattern) {
        StringBuilder pattern = new StringBuilder(inputPattern);
        Notations notations = new Notations();
        pattern = notations.toPrefix(pattern);
        AutomataBuilder automata = new AutomataBuilder();
        headNode = automata.build(pattern);
    }

    @Override
    public boolean isMatched(String text) {
        AtomicInteger position = new AtomicInteger();
        return checkNode(headNode, text, position);
    }

    private boolean checkNode(Node head, String text, AtomicInteger position) {
        boolean result = false;
        if (position.get() <= text.length() - 1) {
            if (head.containsKey(text.charAt(position.get()))) {
                List<Node> ll = head.getValue(text.charAt(position.get()));
                for (Node x : ll) {
                    int prePosition = position.get();
                    position.incrementAndGet();
                    if (checkNode(x, text, position)) {
                        result = true;
                        break;
                    } else {
                        position.set(prePosition);
                    }
                }
            }
            if (!result && head.containsKey('.')) {
                position.getAndIncrement();
                result = checkNode(head.getValue('.').getFirst(), text, position);
            }
        } else {
            result = head.isFinal();
        }
        return result;
    }
}