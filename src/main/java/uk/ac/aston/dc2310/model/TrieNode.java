package uk.ac.aston.dc2310.model;

import java.util.HashMap;

/**
 * @author George Davies
 * @since 18/06/17.
 */
public class TrieNode {

    private char value;

    private HashMap<Character, TrieNode> children = new HashMap<>();

    public TrieNode() {}

    public TrieNode(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

}
