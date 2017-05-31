package uk.ac.aston.dc2310.util;

import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Dictionary;

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
        long uniquePinYinTransliterations = countUniquePinYinTransliterations(definitionsAsList);
        long uniqueEnglishEquivalents = countUniqueEnglishEquivalents(definitionsAsList);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n=== Statistics ===");
        stringBuilder.append("\nUnique traditional characters  : ").append(uniqueTraditionalCharacters);
        stringBuilder.append("\nUnique simplified characters   : ").append(uniqueSimplifiedCharacters);
        stringBuilder.append("\nUnique PinYin transliterations : ").append(uniquePinYinTransliterations);
        stringBuilder.append("\nUnique English Equivalents     : ").append(uniqueEnglishEquivalents);

        return stringBuilder.toString();
    }

    private static long countUniqueTraditionalCharacters(List<Definition> definitions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Definition definition : definitions) {
            stringBuilder.append(definition.getTraditionalChinese());
        }

        return stringBuilder.toString().chars()
                .distinct()
                .count();
    }

    private static long countUniqueSimplifiedCharacters(List<Definition> definitions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Definition definition : definitions) {
            stringBuilder.append(definition.getSimplifiedChinese());
        }

        return stringBuilder.toString().chars()
                .distinct()
                .count();
    }

    private static long countUniquePinYinTransliterations(List<Definition> definitions) {
        Set<String> allPinYin = new HashSet<>();
        for (Definition definition : definitions) {
            allPinYin.addAll(Arrays.asList(definition.getPinYin().split(" ")));
        }

        return allPinYin.stream()
                .distinct()
                .count();
    }

    private static long countUniqueEnglishEquivalents(List<Definition> definitions) {
        Set<String> allEnglishEquivalents = new HashSet<>();
        for (Definition definition : definitions) {
            allEnglishEquivalents.addAll(definition.getEnglishEquivalents());
        }

        return allEnglishEquivalents.stream()
                .distinct()
                .count();
    }

}
