package com.ramesh.matcher;

import java.util.HashMap;
import java.util.Set;

/**
 * @author rameshv
 */
public class Node {
    protected String data;
    HashMap<String, Node> map = new HashMap<String, Node>();

    public Node(String data) {
        this.data = data;
    }

    public void addToMap(String mapData, Node destination) {
        map.put(mapData, destination);
    }

    public Set<String> getKeys() {
        return map.keySet();
    }

    public String getName() {
        return data;
    }

    public void updateData(String data) {
        this.data = data;
    }
}
