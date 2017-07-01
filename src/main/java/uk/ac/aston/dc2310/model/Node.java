package uk.ac.aston.dc2310.model;

import java.util.HashMap;

/**
 * This class represents a Node which can be found inside of a Trie.
 * Each node has a character value unless it's the root node.
 * Each node can have many children.
 *
 * @author George Davies
 * @since 18/06/17.
 */
class Node {

    private char value;

    private HashMap<Character, Node> children = new HashMap<>();

    public Node() {}

    public Node(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public HashMap<Character, Node> getChildren() {
        return children;
    }

}
