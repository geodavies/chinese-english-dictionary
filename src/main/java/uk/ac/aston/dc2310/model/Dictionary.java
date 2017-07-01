package uk.ac.aston.dc2310.model;

import java.util.*;

/**
 * This class represents a Dictionary that contains Definitions. It contains methods which allow it to be added to and
 * queried.
 *
 * It can also generate statistics about its content.
 *
 * @author George Davies
 * @since 19/05/17.
 */
public class Dictionary {

    private Map<String, Set<Definition>> traditionalIndex;
    private Map<String, Set<Definition>> simplifiedIndex;
    private Map<String, Set<Definition>> pinYinIndex;
    private Map<String, Set<Definition>> englishIndex;

    private Trie prefixIndex;

    public Dictionary() {
        traditionalIndex = new HashMap<>();
        simplifiedIndex = new HashMap<>();
        pinYinIndex = new HashMap<>();
        englishIndex = new HashMap<>();
        prefixIndex = new Trie();
    }

    /**
     * Adds the given definition to the dictionary and adds indexes for fast querying.
     *
     * @param definition the definition to add
     */
    public void addDefinition(Definition definition) {
        // Index by traditional chinese
        String traditionalChinese = definition.getTraditionalChinese();
        if (traditionalIndex.containsKey(traditionalChinese)) { // Index already exists, add to existing
            traditionalIndex.get(traditionalChinese).add(definition);
        } else { // No index exists, create a new one
            traditionalIndex.put(traditionalChinese, new LinkedHashSet<>(Collections.singletonList(definition)));
        }

        // Index by simplified chinese
        String simplifiedChinese = definition.getSimplifiedChinese();
        if (simplifiedIndex.containsKey(simplifiedChinese)) { // Index already exists, add to existing
            simplifiedIndex.get(simplifiedChinese).add(definition);
        } else { // No index exists, create a new one
            simplifiedIndex.put(simplifiedChinese, new LinkedHashSet<>(Collections.singletonList(definition)));
        }

        // Index by PinYin (lower case)
        String lowerPinYin = definition.getPinYin().toLowerCase();
        if (pinYinIndex.containsKey(lowerPinYin)) { // Index already exists, add to existing
            pinYinIndex.get(lowerPinYin).add(definition);
        } else { // No index exists, create a new one
            pinYinIndex.put(lowerPinYin, new LinkedHashSet<>(Collections.singletonList(definition)));
        }

        // Index by english equivalent
        List<String> englishEquivalents = definition.getEnglishEquivalents();
        for (String englishEquivalent : englishEquivalents) {
            if (englishIndex.containsKey(englishEquivalent)) { // Index already exists, add to existing
                englishIndex.get(englishEquivalent).add(definition);
            } else { // No index exists, create a new one
                englishIndex.put(englishEquivalent, new LinkedHashSet<>(Collections.singletonList(definition)));
            }
        }

        // Index by prefix
        prefixIndex.add(definition.getTraditionalChinese());
    }

    /**
     * Returns all definitions that share the given chinese in traditional or simplified.
     * If none are found then return an empty set.
     *
     * @param chinese the chinese string to query
     * @return set of definitions or empty list
     */
    public Set<Definition> getDefinitionByTraditionalOrSimplified(String chinese) {
        Set<Definition> results = new LinkedHashSet<>(); // Set to avoid duplicate results

        if (traditionalIndex.containsKey(chinese)) { // Found in index, definitions add to results
            results.addAll(traditionalIndex.get(chinese));
        }

        if (simplifiedIndex.containsKey(chinese)) { // Found in index, definitions add to results
            results.addAll(simplifiedIndex.get(chinese));
        }

        return results;
    }

    /**
     * Returns all definitions that share the given PinYin.
     * If none are found then return an empty set.
     *
     * @param pinYin the PinYin to query
     * @return set of definitions or empty set
     */
    public Set<Definition> getDefinitionsByPinYin(String pinYin) {
        Set<Definition> pinYinDefinitions = pinYinIndex.get(pinYin.toLowerCase());
        return (pinYinDefinitions == null) ? Collections.emptySet() : pinYinDefinitions;
    }

    /**
     * Returns all definitions that share the given english translation.
     * If none are found then return an empty set.
     *
     * @param english the english to query
     * @return set of definitions or empty set
     */
    public Set<Definition> getDefinitionsByEnglishEquivalent(String english) {
        Set<Definition> englishDefinitions = englishIndex.get(english);
        return (englishDefinitions == null) ? Collections.emptySet() : englishDefinitions;
    }

    /**
     * Returns all definitions that share the given chinese prefix.
     * If the prefix isn't a real word or none are found then return an empty set.
     *
     * @param prefix the shared chinese prefix to query
     * @return set of definitions or empty set
     */
    public Set<Definition> getDefinitionsByTraditionalPrefix(String prefix) {
        Set<Definition> prefixDefinition = getDefinitionByTraditionalOrSimplified(prefix);

        if (prefixDefinition.size() == 0) {
            return Collections.emptySet();
        } else {
            Node node = prefixIndex.getPrefixNode(prefix);
            List<String> paths = prefixIndex.getAllPathsForNode(node);

            Set<Definition> prefixDefinitions = new LinkedHashSet<>();

            if (paths.size() > 1) {
                prefixDefinitions.addAll(prefixDefinition);
            }

            for (String path : paths) {
                prefixDefinitions.addAll(getDefinitionByTraditionalOrSimplified(prefix + path));
            }
            return prefixDefinitions;
        }
    }

    /**
     * Collects and formats statistics about the dictionary to confirm correctness.
     *
     * @return formatted statistics String
     */
    public String getSummaryStatistics() {
        long associatedPrefixesCount = 0;

        // For each unique traditional word, count all words sharing the same prefix
        for (String traditional : traditionalIndex.keySet()) {
            Set<Definition> prefixDefinitions = getDefinitionsByTraditionalPrefix(traditional);
            associatedPrefixesCount += prefixDefinitions.size();
        }

        long uniqueTraditionalWords = traditionalIndex.size();
        long uniqueSimplifiedWords = simplifiedIndex.size();
        long uniquePinYinTransliterations = pinYinIndex.size();
        long uniqueEnglishEquivalents = englishIndex.size();

        return "\n=== Statistics ===" +
                "\nTraditional words              : " + uniqueTraditionalWords +
                "\nSimplified words               : " + uniqueSimplifiedWords +
                "\nUnique PinYin transliterations : " + uniquePinYinTransliterations +
                "\nUnique English Equivalents     : " + uniqueEnglishEquivalents +
                "\nPotential Chinese prefixes     : " + uniqueTraditionalWords +
                "\nWords associated to prefixes   : " + associatedPrefixesCount +
                "\nActual prefixes                : " + uniqueEnglishEquivalents;
    }

}
