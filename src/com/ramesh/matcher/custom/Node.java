package com.ramesh.matcher.custom;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author rameshv
 */
public class Node {
    private boolean isFinalNode;
    private Map<Character, LinkedList<Node>> map = new HashMap<>();

    public void addToMap(char data, Node destination) {
        LinkedList<Node> tmpList = new LinkedList<>();
        if (map.containsKey(data)) {
            tmpList = map.get(data);
        }

        tmpList.add(destination);
        map.put(data, tmpList);
    }


    public boolean isFinal() {
        return isFinalNode;
    }

    public void setFinal(boolean isFinal) {
        this.isFinalNode = isFinal;
    }

    public boolean containsKey(char key) {
        return map.containsKey(key);
    }

    public LinkedList<Node> getValue(char key) {
        return map.get(key);
    }

}
