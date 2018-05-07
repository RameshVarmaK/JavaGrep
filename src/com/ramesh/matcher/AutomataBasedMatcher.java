package com.ramesh.matcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AutomataBasedMatcher implements StringMatcher {
    private LinkedList<Node> nodesList = new LinkedList<>();
    private String pattern;
    private AtomicInteger reachedEnd = new AtomicInteger();

    public AutomataBasedMatcher(String pattern) {
        this.pattern = pattern;
        constructFSA();
    }

    /**
     * constructing an automata
     */
    public boolean canAccept(char c) {
        if (((int) c == 46) || ((int) c >= 65 && (int) c <= 122)) {
            return true;
        }
        return false;
    }

    public void createMapEntry(Node previous, Node present, Node next, char wildCardCharacter, int reversePosition) {
        if(next.getName().contains("(")){
            int pos=nodesList.indexOf(next);
            next=nodesList.get(pos+1);
        }
        String val=next.getName().replace(wildCardCharacter,' ');
        val=val.replace(')',' ');
        switch (wildCardCharacter) {
            case '*':
                /* previous-next,self loop */
                previous.addToMap(val, next);
                if (present != null) {
                    present.addToMap(present.data, present);
                } else {
                    nodesList.get(reversePosition).addToMap(previous.data, previous);
                }

                break;
            case '+':
                /* self loop */
                if (present != null) {
                    present.addToMap(present.data, present);
                } else {
                    nodesList.get(reversePosition).addToMap(previous.data, previous);
                }
                break;
            case '?':
                /* previous-next */
                previous.addToMap(val, next);
                break;
        }
    }

    public LinkedList<Node> constructFSA() {
        /* creating a required nodes */
        Node sNode = new Node("S");
        StringBuilder str = new StringBuilder(pattern);
        nodesList.add(sNode);
        for (int i = 0; i < str.length(); i++) {
            if (canAccept(str.charAt(i))) {
                /* move till the operator and make a bunch */
                int tillPosition = i;
                while (tillPosition < str.length() - 1) {
                    if (str.charAt(tillPosition) != ')' && canAccept(str.charAt(tillPosition)) == false) {
                        break;
                    }
                    tillPosition += 1;
                }
                if (tillPosition == str.length() - 2) {
                    if (canAccept(str.charAt(tillPosition + 1))) {
                        tillPosition++;
                    }
                }
                Node dNode = new Node(str.substring(i, tillPosition + 1));
                nodesList.add(dNode);
                i = tillPosition;
            } else if (str.charAt(i) == '(') {
                Node dataNode = new Node(str.substring(i, i + 1));
                nodesList.add(dataNode);
            }
        }

        Node eNode = new Node("E");
        nodesList.add(eNode);

        /* adding the links */

        for (int i = 1; i < nodesList.size() - 1; i++) {
            if (nodesList.get(i).getName().contains("(")) {
              int tillPosition=i+1;
                /*changing till position*/
                int subCount=0;
                while (subCount>=0&&tillPosition<nodesList.size()-1) {
                    if(nodesList.get(tillPosition).getName().contains("(") == true){
                        subCount++;
                    }
                    else if(nodesList.get(tillPosition).getName().contains(")") == true){
                        subCount--;
                    }
                    if(subCount>=0){
                        tillPosition++;
                    }

                }
                nodesList.get(tillPosition).updateData(nodesList.get(tillPosition).getName().replace(')', ' '));
                char wildCardCharacter = nodesList.get(tillPosition).getName().charAt(nodesList.get(tillPosition).getName().length() - 1);
                nodesList.get(tillPosition).updateData(nodesList.get(tillPosition).getName().replace(wildCardCharacter, ' '));
                nodesList.remove(i);
                i--;
                if (tillPosition - (i + 1) == 1) {
                    createMapEntry(nodesList.get(i), nodesList.get(i + 1), nodesList.get(tillPosition), wildCardCharacter, 0);
                } else {
                    createMapEntry(nodesList.get(i), null, nodesList.get(tillPosition), wildCardCharacter, tillPosition-1);
                }

            } else {

                char wildCardCharacter = nodesList.get(i).getName().charAt(nodesList.get(i).getName().length() - 1);
                if (!canAccept(wildCardCharacter)) {
                    nodesList.get(i).updateData(nodesList.get(i).getName().replace(wildCardCharacter, ' '));
                }
                nodesList.get(i).updateData(nodesList.get(i).getName().replace(')', ' '));
                createMapEntry(nodesList.get(i - 1), nodesList.get(i), nodesList.get(i+1), wildCardCharacter, 0);
            }
        }

        for (int i = 0; i < nodesList.size() - 1; i++) {
            nodesList.get(i).map.put(nodesList.get(i + 1).data, nodesList.get(i + 1));
        }
        for (int i = 0; i < nodesList.size(); i++) {
			System.out.println(nodesList.get(i) + " " + nodesList.get(i).getName() + nodesList.get(i).map.toString());
		}
        return nodesList;
    }

    public boolean isEqual(String pattern, String text, AtomicInteger textPosition) {
        if (pattern == "S") {
            return checkNode(nodesList.getFirst(), text, textPosition);
        }
        if (pattern == "E") {
            textPosition.incrementAndGet();
            return checkNode(nodesList.getLast(), text, textPosition);
        }
        int preservedPosition = textPosition.get();
        pattern = pattern.trim();

        if (pattern.length() > (text.length() - textPosition.get())) {
            return false;
        }
        for (int i = 0; i <= pattern.length() - 1; i++) {
            if (pattern.charAt(i) == '.' || pattern.charAt(i) == text.charAt(textPosition.get())) {
                textPosition.incrementAndGet();
            } else {
                textPosition.set(preservedPosition);
                return false;
            }
        }
        return true;
    }

    public boolean checkNode(Node node, String text, AtomicInteger textPosition) {
        Set<String> keys;
        keys = node.getKeys();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keys);
        while (node.getName() != "E") {
            boolean isPresent = false;
            for (int j = keyList.size() - 1; j >= 0; j--) {
                boolean isEqual = isEqual(keyList.get(j), text, textPosition);
                if (isEqual == true) {
                    isPresent = true;
                    if (textPosition.get() <= text.length()) {
                        isPresent = checkNode(node.map.get((keyList.get(j).toString())), text, textPosition);
                    }
                    if (reachedEnd.get() == 1 && textPosition.get() >= text.length()) {
                        return isEqual;
                    } else {
                        return false;
                    }
                }
            }
            if (isPresent == false) {
                return false;
            }
        }
        reachedEnd.incrementAndGet();
        if (textPosition.get() >= text.length()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isMatched(String text) {
        AtomicInteger textPosition = new AtomicInteger();
        reachedEnd.set(0);
        return checkNode(nodesList.getFirst(), text, textPosition);
    }
}