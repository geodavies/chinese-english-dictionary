package uk.ac.aston.dc2310.model;

import java.util.*;

/**
 * @author George Davies
 * @since 19/05/17.
 */
public class Dictionary {

    private List<Definition> definitions;

    private Map<String, Definition> traditionalIndex;
    private Map<String, Definition> simplifiedIndex;

    private Map<String, List<Definition>> pinYinIndex;
    private Map<String, List<Definition>> englishIndex;

    private Trie prefixIndex;

    public Dictionary() {
        definitions = new ArrayList<>();
        traditionalIndex = new HashMap<>();
        simplifiedIndex = new HashMap<>();
        pinYinIndex = new HashMap<>();
        englishIndex = new HashMap<>();
        prefixIndex = new Trie();
    }

    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }

    public Definition getDefinitionByTraditionalOrSimplified(String chinese) {
        if (traditionalIndex.containsKey(chinese)) {
            return traditionalIndex.get(chinese);
        } else {
            return simplifiedIndex.getOrDefault(chinese, null);
        }
    }

    public List<Definition> getDefinitionsByTraditionalPrefix(String prefix) {
        Definition prefixDefinition = getDefinitionByTraditionalOrSimplified(prefix);

        if (prefixDefinition == null) {
            return Collections.emptyList();
        } else {
            Node node = prefixIndex.getPrefixNode(prefix);
            List<String> paths = prefixIndex.getAllPathsForNode(node);

            List<Definition> prefixDefinitions = new ArrayList<>();

            if (paths.size() > 1) {
                prefixDefinitions.add(prefixDefinition);
            }

            for (String path : paths) {
                prefixDefinitions.add(getDefinitionByTraditionalOrSimplified(prefix + path));
            }
            return prefixDefinitions;
        }
    }

    public List<Definition> getDefinitionsByPinYin(String pinYin) {
        List<Definition> pinYinDefinitions = pinYinIndex.get(pinYin.toLowerCase());
        return (pinYinDefinitions == null) ? Collections.emptyList() : pinYinDefinitions;
    }

    public List<Definition> getDefinitionsByEnglishEquivalent(String english) {
        List<Definition> englishDefinitions = englishIndex.get(english);
        return (englishDefinitions == null) ? Collections.emptyList() : englishDefinitions;
    }

    public void index() {
        for (Definition definition : definitions) {
            traditionalIndex.put(definition.getTraditionalChinese(), definition);
            simplifiedIndex.put(definition.getSimplifiedChinese(), definition);

            String lowerPinYin = definition.getPinYin().toLowerCase();
            if (pinYinIndex.containsKey(lowerPinYin)) {
                pinYinIndex.get(lowerPinYin).add(definition);
            } else {
                pinYinIndex.put(lowerPinYin, new ArrayList<>(Collections.singletonList(definition)));
            }

            List<String> englishEquivalents = definition.getEnglishEquivalents();
            for (String englishEquivalent : englishEquivalents) {
                if (englishIndex.containsKey(englishEquivalent)) {
                    englishIndex.get(englishEquivalent).add(definition);
                } else {
                    englishIndex.put(englishEquivalent, new ArrayList<>(Collections.singletonList(definition)));
                }
            }

            prefixIndex.add(definition.getTraditionalChinese());
        }
    }

    public List<Definition> getDefinitionsAsList() {
        return definitions;
    }

}
