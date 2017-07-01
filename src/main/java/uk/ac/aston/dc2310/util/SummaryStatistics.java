package uk.ac.aston.dc2310.util;

import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Dictionary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author George Davies
 * @since 20/05/17.
 */
public class SummaryStatistics {

    public static String getSummaryStatistics(Dictionary dictionary) {
        List<Definition> definitions = dictionary.getDefinitionsAsList();

        Set<String> traditionalWords = new HashSet<>();
        Set<String> simplifiedWords = new HashSet<>();
        Set<String> pinYinTransliterations = new HashSet<>();
        Set<String> englishEquivalents = new HashSet<>();

        long associatedPrefixesCount = 0;

        for (Definition definition : definitions) {
            traditionalWords.add(definition.getTraditionalChinese());
            simplifiedWords.add(definition.getSimplifiedChinese());
            pinYinTransliterations.add(definition.getPinYin().toLowerCase());
            englishEquivalents.addAll(definition.getEnglishEquivalents());

            List<Definition> prefixDefinitions = dictionary.getDefinitionsByTraditionalPrefix(definition.getTraditionalChinese());
            associatedPrefixesCount += prefixDefinitions.size();
        }

        long uniqueTraditionalWords = traditionalWords.size();
        long uniqueSimplifiedWords = simplifiedWords.size();
        long uniquePinYinTransliterations = pinYinTransliterations.size();
        long uniqueEnglishEquivalents = englishEquivalents.size();

        return "\n=== Statistics ===" +
                "\nNumber of traditional words              : " + uniqueTraditionalWords +
                "\nNumber of simplified words               : " + uniqueSimplifiedWords +
                "\nUnique PinYin transliterations           : " + uniquePinYinTransliterations +
                "\nUnique English Equivalents               : " + uniqueEnglishEquivalents +
                "\nNumber of potential Chinese prefixes     : " + uniqueTraditionalWords +
                "\nNumber of words associated to prefixes   : " + associatedPrefixesCount;
    }

}
