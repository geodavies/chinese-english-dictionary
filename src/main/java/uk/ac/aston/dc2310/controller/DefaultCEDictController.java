package uk.ac.aston.dc2310.controller;

import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Dictionary;

import java.util.Set;

/**
 * This class is an implementation of CEDictController which allows the user to interface with the application.
 *
 * @author George Davies
 * @since 31/05/17
 */
public class DefaultCEDictController implements CEDictController {

    private Dictionary dictionary;

    public DefaultCEDictController(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String summaryStatistics() {
        return dictionary.getSummaryStatistics();
    }

    @Override
    public String lookupChinese(String chineseWord) {
        Set<Definition> queryResults = dictionary.getDefinitionByTraditionalOrSimplified(chineseWord);
        if (queryResults.isEmpty()) {
            return "\nNo results found for Chinese query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    @Override
    public String lookupEnglish(String English) {
        Set<Definition> queryResults = dictionary.getDefinitionsByEnglishEquivalent(English);
        if (queryResults.isEmpty()) {
            return "\nNo results found for English query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    @Override
    public String lookupPinyin(String pinyin) {
        // Remove square brackets before querying dictionary
        String strippedPinYin = pinyin.replaceAll("[\\[\\]]", "");
        Set<Definition> queryResults = dictionary.getDefinitionsByPinYin(strippedPinYin);
        if (queryResults.isEmpty()) {
            return "\nNo results found for PinYin query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    @Override
    public String lookupChinesePrefix(String chinesePrefix) {
        Set<Definition> queryResults = dictionary.getDefinitionsByTraditionalPrefix(chinesePrefix);
        if (queryResults.isEmpty()) {
            return "\nNo results found for Prefix query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    /**
     * Takes a set of definitions and appends all of the toString results to a String with spaces in-between each.
     *
     * @param definitions the definitions to format
     * @return the formatted String
     */
    private String formatDefinitionsToString(Set<Definition> definitions) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Definition definition : definitions) {
            stringBuilder.append("\n").append(definition.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

}
