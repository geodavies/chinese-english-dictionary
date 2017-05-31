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
        System.out.println();

        for (Definition definition : definitions) {
            int definitionNumber = definitions.indexOf(definition) + 1;
            System.out.println("=== Definition " + definitionNumber + " ===");
            System.out.println("Traditional Chinese : " + definition.getTraditionalChinese());
            System.out.println("Simplified Chinese  : " + definition.getSimplifiedChinese());
            System.out.println("PinYin              : " + definition.getPinYin());

            List<String> englishEquivalents = definition.getEnglishEquivalents();
            System.out.print("English Equivalents : ");
            boolean first = true;
            for (String englishEquivalent : englishEquivalents) {
                if (first) {
                    System.out.print(englishEquivalent + "\n");
                    first = false;
                } else {
                    System.out.println("                      " + englishEquivalent);
                }
            }

            System.out.println();
        }
    }

    @Override
    public void printStatistics(long uniqueTraditional, long uniqueSimplified, long uniquePinYin, long uniqueEnglish) {
        System.out.println();

        System.out.println("=== Statistics ===");
        System.out.println("Unique traditional characters  : " + uniqueTraditional);
        System.out.println("Unique simplified characters   : " + uniqueSimplified);
        System.out.println("Unique PinYin transliterations : " + uniquePinYin);
        System.out.println("Unique English Equivalents     : " + uniqueEnglish);

        System.out.println();
    }

}
