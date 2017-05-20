package uk.ac.aston.dc2310.formatter;

import uk.ac.aston.dc2310.model.Definition;

import java.util.List;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public class BasicOutputFormatter implements OutputFormatter {

    @Override
    public void printDefinitions(List<Definition> definitions) {
        for (Definition definition : definitions) {
            int definitionNumber = definitions.indexOf(definition) + 1;
            System.out.println("\n~~~ Definition " + definitionNumber + " ~~~");
            System.out.println("Traditional Chinese: " + definition.getTraditionalChinese());
            System.out.println("Simplified Chinese: " + definition.getSimplifiedChinese());
            System.out.println("PinYin: " + definition.getPinYin());
            List<String> englishEquivalents = definition.getEnglishEquivalents();
            System.out.println("--- English Equivalents ---");
            for (String englishEquivalent : englishEquivalents) {
                System.out.println(englishEquivalent);
            }
        }
    }

}
