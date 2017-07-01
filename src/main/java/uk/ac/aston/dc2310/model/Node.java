package uk.ac.aston.dc2310.model;

import java.util.HashMap;

/**
 * @author George Davies
 * @since 18/06/17.
 */
class Node {

    private char value;

    private HashMap<Character, Node> children = new HashMap<>();

    Node() {}

    Node(char value){
        this.value = value;
    }

    char getValue() {
        return value;
    }

    HashMap<Character, Node> getChildren() {
        return children;
    }

}
