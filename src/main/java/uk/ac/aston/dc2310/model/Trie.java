package uk.ac.aston.dc2310.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George Davies
 * @since 18/06/17.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String word) {
        HashMap<Character, TrieNode> children = root.getChildren();

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            TrieNode trieNode;
            if(children.containsKey(c)){
                trieNode = children.get(c);
            }else{
                trieNode = new TrieNode(c);
                children.put(c, trieNode);
            }

            children = trieNode.getChildren();
        }
    }

    public TrieNode getPrefixNode (String prefix) {
        Map<Character, TrieNode> children = root.getChildren();

        TrieNode trieNode = null;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (children.containsKey(c)) {
                trieNode = children.get(c);
                children = trieNode.getChildren();
            } else {
                return null;
            }
        }

        return trieNode;
    }

    public List<String> getAllPathsForNode(TrieNode node) {
        List<String> paths = new ArrayList<>();
        char path[] = new char[20];

        getPathsRecursively(node, path, 0, paths);

        return paths;
    }

    private void getPathsRecursively (TrieNode node, char path[], int pathLen, List<String> outputPaths) {
        // Add current node to path
        path[pathLen] = node.getValue();
        pathLen++;

        if (node.getChildren().size() == 0) {
            // This is a leaf so add path to output
            outputPaths.add(String.valueOf(path).trim().substring(1, pathLen));
        } else {
            // Not a leaf so keep going
            for (TrieNode subNode : node.getChildren().values()) {
                getPathsRecursively(subNode, path, pathLen, outputPaths);
            }
        }
    }

}