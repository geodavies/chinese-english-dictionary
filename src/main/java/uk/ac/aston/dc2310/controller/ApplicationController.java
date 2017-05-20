package uk.ac.aston.dc2310.controller;

import uk.ac.aston.dc2310.formatter.OutputFormatter;
import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Definitions;
import uk.ac.aston.dc2310.model.OperatingMode;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public class ApplicationController {

    private Definitions definitions;
    private OutputFormatter outputFormatter;

    private OperatingMode currentOperatingMode = null;

    public ApplicationController(Definitions definitions, OutputFormatter outputFormatter) {
        this.definitions = definitions;
        this.outputFormatter = outputFormatter;
    }

    public void start() {
        Pattern commandPattern = Pattern.compile("^mode (.+)$");

        while (true) {
            String userInput = getUserInput();
            Matcher commandMatcher = commandPattern.matcher(userInput);
            if (commandMatcher.matches()) {
                String selectedMode = commandMatcher.group(1);
                try {
                    currentOperatingMode = OperatingMode.valueOf(selectedMode.toLowerCase());
                    System.out.println("Operating mode set to " + currentOperatingMode.toString());
                } catch (IllegalArgumentException e) {
                    System.out.println("Mode invalid!");
                }
            } else {
                if (currentOperatingMode == null) {
                    System.out.println("Please select an operating mode first");
                } else {
                    handleUserSearchQuery(userInput);
                }
            }
        }
    }

    private String getUserInput() {
        Scanner reader = new Scanner(System.in);
        System.out.print(">");
        return reader.nextLine();
    }

    private void handleUserSearchQuery(String userInput) {
        switch (currentOperatingMode) {
            case chinese: {
                List<Definition> definitionResults = definitions.getDefinitionsByTraditionalOrSimplified(userInput);
                if (definitionResults.isEmpty()) {
                    System.out.println("No results found for Chinese query");
                } else {
                    outputFormatter.printDefinitions(definitionResults);
                }
                break;
            }
            case pinyin: {
                List<Definition> definitionResults =  definitions.getDefinitionsByPinYin(userInput);
                if (definitionResults.isEmpty()) {
                    System.out.println("No results found for PinYin query");
                } else {
                    outputFormatter.printDefinitions(definitionResults);
                }
                break;
            }
            case english: {
                List<Definition> definitionResults =  definitions.getDefinitionsByEnglishEquivalent(userInput);
                if (definitionResults.isEmpty()) {
                    System.out.println("No results found for English query");
                } else {
                    outputFormatter.printDefinitions(definitionResults);
                }
                break;
            }
            case prefix: {
                List<Definition> definitionResults =  definitions.getDefinitionsByTraditionalPrefix(userInput);
                if (definitionResults.isEmpty()) {
                    System.out.println("No results found for Prefix query");
                } else {
                    outputFormatter.printDefinitions(definitionResults);
                }
                break;
            }
        }
    }

}
