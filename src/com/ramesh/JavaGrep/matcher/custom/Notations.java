package com.ramesh.JavaGrep.matcher.custom;

import java.util.Stack;

public class Notations {
    private void processOperator(Stack<Character> operatorStack, StringBuilder resultPattern, char operator) {
        if (operatorStack.size() > 0 && !operatorStack.contains('(')) {
            resultPattern.append(operatorStack.pop());
        }
        operatorStack.push(operator);
    }

    private void processOperand(Stack<Character> operatorStack, StringBuilder resultPattern, char operand) {
        if (!operatorStack.contains('(')) {
            resultPattern.append('$');
        }
        resultPattern.append(operand);
        if (!operatorStack.contains('(') && operatorStack.size() > 0) {
            resultPattern.append(operatorStack.pop());
        }
    }

    private void closingBrace(Stack<Character> operatorStack, StringBuilder resultPattern) {
        while (operatorStack.size() > 0 && operatorStack.peek() != '(') {
            resultPattern.append(operatorStack.pop());
        }
        operatorStack.pop();
    }

    private void openingBrace(Stack<Character> operatorStack, StringBuilder resultPattern, char operator) {
        resultPattern.append('$');
        operatorStack.push(operator);
    }

    public StringBuilder toPostfix(StringBuilder pattern) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder resultPattern = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            if (Operator.get(pattern.charAt(i)).isPresent()) {
                processOperator(operatorStack, resultPattern, pattern.charAt(i));
            } else if (pattern.charAt(i) == '(') {
                openingBrace(operatorStack, resultPattern, pattern.charAt(i));
            } else if (pattern.charAt(i) == ')') {
                closingBrace(operatorStack, resultPattern);
            } else {
                processOperand(operatorStack, resultPattern, pattern.charAt(i));
            }
        }
        while (operatorStack.size() != 0) {
            resultPattern.append(operatorStack.pop());
        }
        return resultPattern;
    }

    public StringBuilder toPrefix(StringBuilder pattern) {
        pattern.reverse();
        /*changing the braces*/
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '(') {
                pattern.setCharAt(i, ')');
            } else if (pattern.charAt(i) == ')') {
                pattern.setCharAt(i, '(');
            }
        }
        pattern = toPostfix(pattern).reverse();

        return pattern;
    }
}