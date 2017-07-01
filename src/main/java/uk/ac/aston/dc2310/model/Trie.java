package uk.ac.aston.dc2310.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George Davies
 * @since 18/06/17.
 */
class Trie {

    private Node root;

    Trie() {
        root = new Node();
    }

    void add(String word) {
        HashMap<Character, Node> children = root.getChildren();

        for (char c : word.toCharArray()) { // For each character in the word
            Node node;
            if (children.containsKey(c)) { // Char under the root: Select node
                node = children.get(c);
            } else { // Char not under root: Add new node
                node = new Node(c);
                children.put(c, node);
            }

            children = node.getChildren();
        }
    }

    Node getPrefixNode(String prefix) {
        Map<Character, Node> children = root.getChildren();
        Node node = null;
        for (char c : prefix.toCharArray()) {
            if (children.containsKey(c)) {
                node = children.get(c);
                children = node.getChildren();
            } else {
                return null;
            }
        }
        return node;
    }

    List<String> getAllPathsForNode(Node node) {
        List<String> paths = new ArrayList<>();
        char path[] = new char[20];
        getPathsRecursively(node, path, 0, paths);
        return paths;
    }

    private void getPathsRecursively(Node node, char path[], int pathLen, List<String> outputPaths) {
        // Add current node to path
        path[pathLen] = node.getValue();
        pathLen++;

        if (node.getChildren().size() == 0) {
            // This is a leaf so add path to output
            outputPaths.add(String.valueOf(path).trim().substring(1, pathLen));
        } else {
            // Not a leaf so keep going
            for (Node subNode : node.getChildren().values()) {
                getPathsRecursively(subNode, path, pathLen, outputPaths);
            }
        }
    }

}