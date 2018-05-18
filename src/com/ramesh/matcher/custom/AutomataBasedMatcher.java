package com.ramesh.matcher.custom;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class AutomataBasedMatcher implements StringMatcher {
    private Node headNode;

    public AutomataBasedMatcher(String inputPattern) {
        StringBuilder pattern = new StringBuilder(inputPattern);
        pattern = Notations.toPrefix(pattern);
        automataBuilder automata = new automataBuilder();
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
            if (head.containsKey('.')) {
                position.getAndIncrement();
                result = checkNode(head.getValue('.').getFirst(), text, position);
            } else if (head.containsKey(text.charAt(position.get()))) {
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
        } else {
            result = head.isFinal();
        }
        return result;
    }
}