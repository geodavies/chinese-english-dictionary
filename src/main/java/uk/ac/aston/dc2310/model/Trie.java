package uk.ac.aston.dc2310.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Trie data structure which is made up of Nodes.
 * Words can be added to and searched from the Trie.
 * Additional methods are present to search for given prefixes.
 *
 * @author George Davies
 * @since 18/06/17.
 */
class Trie {

    private Node root;

    Trie() {
        root = new Node();
    }

    /**
     * Adds a given word to the Trie, placing each character of word as children of one another.
     *
     * @param word the word to add
     */
    public void add(String word) {
        HashMap<Character, Node> children = root.getChildren();

        for (char c : word.toCharArray()) { // For each character in the word
            Node node;
            if (children.containsKey(c)) { // Char under child: Select node
                node = children.get(c);
            } else { // Char not under child: Add new node
                node = new Node(c);
                children.put(c, node);
            }

            children = node.getChildren();
        }
    }

    /**
     * Returns the node associated with the given word, if it can't be found then return null.
     *
     * @param prefix the word to find
     * @return the associated Node
     */
    public Node getPrefixNode(String prefix) {
        Map<Character, Node> children = root.getChildren();
        Node node = null;
        for (char c : prefix.toCharArray()) { // For each character in the word
            if (children.containsKey(c)) { // Char under child: Select node
                node = children.get(c);
                children = node.getChildren();
            } else { // Char not under child: No matching prefix, return null
                return null;
            }
        }
        return node;
    }

    /**
     * Gets a list of all the possible paths that can be created under a given Node.
     *
     * @param node the node to search
     * @return List of paths
     */
    public List<String> getAllPathsForNode(Node node) {
        List<String> paths = new ArrayList<>();
        char path[] = new char[20];
        getPathsRecursively(node, path, 0, paths);
        return paths;
    }

    /**
     * Recursively searches all nodes under a given node and populates outputPaths with the complete paths.
     *
     * @param node the node to start the search from
     * @param path a char array to store each character of the current path in
     * @param pathLen the current path length
     * @param outputPaths List of Strings to be populated with all found paths
     */
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