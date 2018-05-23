package com.ramesh.matcher.custom;

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

    public static Operator get(Object value) {
        Operator[] values = Operator.values();
        for (Operator operator : values) {
            if (value.equals(operator.symbol)) {
                return operator;
            }
        }
        return null;
    }

    public abstract Node addToFSA(Node prePosition, Node current, char inputSymbol);

}