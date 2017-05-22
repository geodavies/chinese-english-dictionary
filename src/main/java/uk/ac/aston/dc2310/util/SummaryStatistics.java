package uk.ac.aston.dc2310.util;

import uk.ac.aston.dc2310.formatter.OutputFormatter;
import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Definitions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author George Davies
 * @since 20/05/17.
 */
public class SummaryStatistics {

    public static void printSummaryStatistics(Definitions definitions, OutputFormatter outputFormatter) {
        List<Definition> definitionsAsList = definitions.getDefinitionsAsList();

        long uniqueTraditionalCharacters = countUniqueTraditionalCharacters(definitionsAsList);
        long uniqueSimplifiedCharacters = countUniqueSimplifiedCharacters(definitionsAsList);
        long uniquePinYinTransliterations = countUniquePinYinTransliterations(definitionsAsList);
        long uniqueEnglishEquivalents = countUniqueEnglishEquivalents(definitionsAsList);

        outputFormatter.printStatistics(uniqueTraditionalCharacters, uniqueSimplifiedCharacters,
                uniquePinYinTransliterations, uniqueEnglishEquivalents);
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
        Set<String> allEnglishTransliterations = new HashSet<>();
        for (Definition definition : definitions) {
            allEnglishTransliterations.addAll(definition.getEnglishEquivalents());
        }

        return allEnglishTransliterations.stream()
                .distinct()
                .count();
    }

}
