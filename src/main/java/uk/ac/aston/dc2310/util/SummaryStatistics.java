package uk.ac.aston.dc2310.util;

import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Dictionary;
import uk.ac.aston.dc2310.model.Trie;
import uk.ac.aston.dc2310.model.TrieNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author George Davies
 * @since 20/05/17.
 */
public class SummaryStatistics {

    public static String getSummaryStatistics(Dictionary dictionary) {
        List<Definition> definitionsAsList = dictionary.getDefinitionsAsList();

        long uniqueTraditionalCharacters = countUniqueTraditionalCharacters(definitionsAsList);
        long uniqueSimplifiedCharacters = countUniqueSimplifiedCharacters(definitionsAsList);
        long uniqueTraditionalWords = countUniqueTraditionalWords(definitionsAsList);
        long uniqueSimplifiedWords = countUniqueSimplifiedWords(definitionsAsList);
        long uniquePinYinTransliterationsOld = countUniquePinYinTransliterationsOld(definitionsAsList);
        long uniquePinYinTransliterations = countUniquePinYinTransliterations(definitionsAsList);
        long uniqueEnglishEquivalents = countUniqueEnglishEquivalents(definitionsAsList);

        Trie prefixIndex = dictionary.getPrefixIndex();
        TrieNode rootNode = prefixIndex.getRoot();

        dictionary.getPrefixIndex().getPrefixCountRecusively(rootNode);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n=== Statistics ===");
        stringBuilder.append("\nNumber of unique traditional characters : ").append(uniqueTraditionalCharacters);
        stringBuilder.append("\nNumber of unique simplified characters  : ").append(uniqueSimplifiedCharacters);
        stringBuilder.append("\nNumber of traditional words             : ").append(uniqueTraditionalWords);
        stringBuilder.append("\nNumber of simplified words              : ").append(uniqueSimplifiedWords);
        stringBuilder.append("\nUnique PinYin transliterations old      : ").append(uniquePinYinTransliterationsOld);
        stringBuilder.append("\nUnique PinYin transliterations          : ").append(uniquePinYinTransliterations);
        stringBuilder.append("\nUnique English Equivalents              : ").append(uniqueEnglishEquivalents);
//        stringBuilder.append("\nPrefix Count                            : ").append(count);

        return stringBuilder.toString();
    }

    private static long countUniqueTraditionalCharacters (List<Definition> definitions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Definition definition : definitions) {
            stringBuilder.append(definition.getTraditionalChinese());
        }

        return stringBuilder.toString().chars()
                .distinct()
                .count();
    }

    private static long countUniqueSimplifiedCharacters (List<Definition> definitions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Definition definition : definitions) {
            stringBuilder.append(definition.getSimplifiedChinese());
        }

        return stringBuilder.toString().chars()
                .distinct()
                .count();
    }

    private static long countUniqueTraditionalWords (List<Definition> definitions) {
        return definitions.stream()
                .map(Definition::getTraditionalChinese)
                .distinct()
                .count();
    }

    private static long countUniqueSimplifiedWords (List<Definition> definitions) {
        return definitions.stream()
                .map(Definition::getSimplifiedChinese)
                .distinct()
                .count();
    }

    private static long countUniquePinYinTransliterationsOld (List<Definition> definitions) {
        Set<String> allPinYin = new HashSet<>();
        for (Definition definition : definitions) {
            allPinYin.addAll(Arrays.asList(definition.getPinYin().split(" ")));
        }

        return allPinYin.stream()
                .distinct()
                .count();
    }

    private static long countUniquePinYinTransliterations (List<Definition> definitions) {
        return definitions.stream()
                .map(Definition::getPinYin)
                .distinct()
                .count();
    }

    private static long countUniqueEnglishEquivalents (List<Definition> definitions) {
        Set<String> allEnglishEquivalents = new HashSet<>();
        for (Definition definition : definitions) {
            allEnglishEquivalents.addAll(definition.getEnglishEquivalents());
        }

        return allEnglishEquivalents.stream()
                .distinct()
                .count();
    }

}
