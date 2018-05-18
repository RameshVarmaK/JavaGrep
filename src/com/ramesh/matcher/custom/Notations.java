package com.ramesh.matcher.custom;

import java.util.Stack;

public class Notations {

    public static StringBuilder toPostfix(StringBuilder pattern) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder resultPattern = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            if (Operator.getNameForValue(pattern.charAt(i)) != null) {
                //If the token is an operator, *, /, +, or -, push it on the opstack. However, first
                // remove any operators already on the opstack that have higher or equal precedence and append them to the output list.
                if (operatorStack.size() > 0 && !operatorStack.contains('(')) {
                       resultPattern.append(operatorStack.pop());
                }
                operatorStack.push(pattern.charAt(i));

            } else if (pattern.charAt(i) == '(') { //If the token is a left parenthesis, push it on the opstack
                resultPattern.append('$');
                operatorStack.push(pattern.charAt(i));
            } else if (pattern.charAt(i) == ')') {//If the token is a right parenthesis, pop the opstack until the
                // corresponding left parenthesis is removed. Append each operator to the end of the output list.
                while (operatorStack.size() > 0 && operatorStack.peek() != '(') {
                    resultPattern.append(operatorStack.pop());
                }
                operatorStack.pop();
            } else {//If the token is an operand, append it to the end of the output list
                if (!operatorStack.contains('(')) {
                    resultPattern.append('$');
                }
                resultPattern.append(pattern.charAt(i));
            }
        }
        while (operatorStack.size() != 0) {
            resultPattern.append(operatorStack.pop());
        }
        return resultPattern;
    }

    public static StringBuilder toPrefix(StringBuilder pattern) {
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
        if(pattern.charAt(pattern.length()-1)=='$'){
            System.out.println(pattern);
        }
        return pattern;
    }
}