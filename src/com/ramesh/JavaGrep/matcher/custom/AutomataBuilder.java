package com.ramesh.JavaGrep.matcher.custom;


import java.util.Optional;

public class AutomataBuilder {

    private void dataLink(Context wrap, StringBuilder pattern, int i) {
        if (wrap.getResValue() != null) {
            wrap.getPointer().addToMap(pattern.charAt(i), wrap.getResValue());
        } else {
            wrap.updateResValue(new Node());
            if (i == pattern.length() - 2) {
                wrap.getResValue().setFinal(true);
            }
        }
        wrap.getCurrent().addToMap(pattern.charAt(i), wrap.getResValue());
        wrap.updateCurrent(wrap.getResValue());
        wrap.updateResValue(null);
    }

    private void operand(Context wrap, char c) {
        if (wrap.getOperator().isPresent() && wrap.getOperator().get() == Operator.STAR) {
            wrap.getPointer().setFinal(true);
            wrap.getPointer().addToMap(c, wrap.getResValue());
        }
        wrap.updatePointer(wrap.getCurrent());
    }

    public Node build(StringBuilder pattern) {
        Context wrap = new Context();
        for (int i = 0; i < pattern.length(); i++) {
            if (!Operator.get(pattern.charAt(i)).equals(Optional.empty())) {
                operand(wrap, pattern.charAt(charPosition(i, pattern)));
            } else if (pattern.charAt(i) == '$') {
                int nextToOperator = charPosition(i, pattern) - 1;
                wrap.setOperator(getOperator(i, pattern));
                if (wrap.getOperator().isPresent()) {
                    i--;
                    wrap.updateResValue(wrap.getOperator().get().addToFSA(wrap.getPointer(), wrap.getCurrent(),
                            pattern.charAt(nextToOperator)));
                }
                if (i == pattern.length() - 1) {
                    wrap.getCurrent().setFinal(true);
                }
            } else {
                dataLink(wrap, pattern, i);
            }
        }
        return wrap.getHead();
    }

    private Optional<Operator> getOperator(int position, StringBuilder pattern) {
        Optional<Operator> operator = Optional.empty();
        while (position >= 0) {
            if (!Operator.get(pattern.charAt(position)).equals(Optional.empty())) {
                operator = Operator.get(pattern.charAt(position));
                pattern.deleteCharAt(position);
                break;
            }
            position--;
        }
        return operator;
    }

    private int charPosition(int position, StringBuilder pattern) {
        int nextToOperator = position + 1;
        while (nextToOperator >= 1) {
            if (!Operator.get(pattern.charAt(nextToOperator - 1)).equals(Optional.empty())) {
                break;
            }
            nextToOperator--;
        }
        return nextToOperator;
    }
}