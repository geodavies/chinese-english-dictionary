package uk.ac.aston.dc2310.controller;

import uk.ac.aston.dc2310.formatter.OutputFormatter;
import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Definitions;
import uk.ac.aston.dc2310.model.OperatingMode;
import uk.ac.aston.dc2310.util.SummaryStatistics;

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

    private OperatingMode currentOperatingMode = OperatingMode.chinese;

    public ApplicationController(Definitions definitions, OutputFormatter outputFormatter) {
        this.definitions = definitions;
        this.outputFormatter = outputFormatter;
    }

    public void start() {
        while (true) {
            String userInput = getUserInput();
            if (userInput.startsWith("/")) {
                handleCommand(userInput);
            } else {
                if (currentOperatingMode == null) {
                    System.out.println("Please select an operating mode first");
                } else {
                    handleSearchQuery(userInput);
                }
            }
        }
    }

    private String getUserInput() {
        Scanner reader = new Scanner(System.in);
        System.out.print(">");
        return reader.nextLine();
    }

    private void handleCommand(String userInput) {
        Pattern commandPattern = Pattern.compile("(?i)^/(mode|stats|quit)( (chinese|pinyin|english|prefix))?$");
        Matcher commandMatcher = commandPattern.matcher(userInput);

        if (commandMatcher.matches()) {
            String command = commandMatcher.group(1);
            if (command.equalsIgnoreCase("mode")) {
                String selectedMode = commandMatcher.group(3);
                try {
                    currentOperatingMode = OperatingMode.valueOf(selectedMode.toLowerCase());
                    System.out.println("Operating mode set to " + currentOperatingMode.toString());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid mode!");
                }
            } else if (command.equalsIgnoreCase("stats")) {
                SummaryStatistics.printSummaryStatistics(definitions, outputFormatter);
            } else if (command.equalsIgnoreCase("quit")) {
                System.out.println("Exiting Application");
                System.exit(0);
            }
        } else {
            System.out.println("Invalid command!");
        }
    }

    private void handleSearchQuery(String userInput) {
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
