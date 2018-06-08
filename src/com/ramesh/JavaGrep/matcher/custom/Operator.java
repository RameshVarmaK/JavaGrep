package com.ramesh.JavaGrep.matcher.custom;

import java.util.Optional;

public enum Operator {
    STAR('*') {
        @Override
        public Node addToFSA(Node prePosition, Node current, char inputSymbol) {
            Node newNode = new Node();
            current.addToMap(inputSymbol, prePosition.getValue(inputSymbol).getLast());
            prePosition.setFinal(current.isFinal());
            return newNode;
        }
    }, PLUS('+') {
        @Override
        public Node addToFSA(Node prePosition, Node current, char inputSymbol) {
            Node newNode = new Node();
            current.addToMap(inputSymbol, prePosition.getValue(inputSymbol).getLast());
            prePosition.setFinal(false);
            return newNode;
        }
    }, QUESTIONMARK('?') {
        @Override
        public Node addToFSA(Node prePosition, Node current, char inputSymbol) {
            Node newNode = new Node();
            prePosition.setFinal(current.isFinal());
            prePosition.setFinal(true);
            return newNode;
        }
    };

    private char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    public static Optional<Operator> get(Object value) {
        Optional<Operator> optionalOperator = Optional.empty();
        Operator[] values = Operator.values();
        for (Operator operator : values) {
            if (value.equals(operator.symbol)) {
                optionalOperator = Optional.of(operator);
                break;
            }
        }
        return optionalOperator;
    }

    public abstract Node addToFSA(Node prePosition, Node current, char inputSymbol);

}