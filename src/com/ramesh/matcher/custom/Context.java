package com.ramesh.matcher.custom;

import java.util.Optional;

public class Context {

    private Node resValue;
    private Node current;
    private Node pointer;
    private Node head;
    private Optional<Operator> operator;

    public Context() {
        resValue = null;
        head = new Node();
        pointer = head;
        current = this.head;
        operator = Optional.empty();
    }

    public void updateCurrent(Node current) {
        this.current = current;
    }

    public void updatePointer(Node pointer) {
        this.pointer = pointer;
    }

    public void updateResValue(Node resValue) {
        this.resValue = resValue;
    }

    public Node getCurrent() {
        return current;
    }

    public Node getHead() {
        return head;
    }

    public Node getPointer() {
        return pointer;
    }

    public Node getResValue() {
        return resValue;
    }

    public Optional<Operator> getOperator() {
        return operator;
    }

    public void setOperator(Optional<Operator> operator) {
        this.operator = operator;
    }
}
