package uk.ac.aston.dc2310.controller;

import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Dictionary;
import uk.ac.aston.dc2310.util.SummaryStatistics;

import java.util.List;

/**
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
        return SummaryStatistics.getSummaryStatistics(dictionary);
    }

    @Override
    public String lookupChinese(String chineseWord) {
        List<Definition> queryResults = dictionary.getDefinitionsByTraditionalOrSimplified(chineseWord);
        if (queryResults.isEmpty()) {
            return "\nNo results found for Chinese query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    @Override
    public String lookupEnglish(String English) {
        List<Definition> queryResults =  dictionary.getDefinitionsByEnglishEquivalent(English);
        if (queryResults.isEmpty()) {
            return "\nNo results found for English query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    @Override
    public String lookupPinyin(String pinyin) {
        String strippedPinYin = pinyin.replaceAll("\\[", "").replaceAll("]", "");
        List<Definition> queryResults =  dictionary.getDefinitionsByPinYin(strippedPinYin);
        if (queryResults.isEmpty()) {
            return "\nNo results found for PinYin query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    @Override
    public String lookupChinesePrefix(String chinesePrefix) {
        List<Definition> queryResults =  dictionary.getDefinitionsByTraditionalPrefix(chinesePrefix);
        if (queryResults.isEmpty()) {
            return "\nNo results found for Prefix query";
        } else {
            return formatDefinitionsToString(queryResults);
        }
    }

    private String formatDefinitionsToString(List<Definition> definitions) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Definition definition : definitions) {
            stringBuilder.append("\n").append(definition.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

}
