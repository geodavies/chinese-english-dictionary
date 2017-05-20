package uk.ac.aston.dc2310;

import uk.ac.aston.dc2310.controller.ApplicationController;
import uk.ac.aston.dc2310.formatter.BasicOutputFormatter;
import uk.ac.aston.dc2310.formatter.OutputFormatter;
import uk.ac.aston.dc2310.formatter.PrettyOutputFormatter;
import uk.ac.aston.dc2310.model.Definitions;
import uk.ac.aston.dc2310.util.DictionaryParser;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public class ChineseEnglishDictionaryApplication {

    public static void main(String[] args) {
        System.out.println("Starting Chinese English Dictionary Application");

        OutputFormatter outputFormatter= null;
        String dictionaryFilePath;

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("basic")) {
                outputFormatter = new BasicOutputFormatter();
            } else if (args[0].equalsIgnoreCase("pretty")) {
                outputFormatter = new PrettyOutputFormatter();
            } else {
                System.out.println("Invalid mode argument, stopping application");
                System.exit(1);
            }
            dictionaryFilePath = args[1];
        } else {
            outputFormatter = getModeInput();
            dictionaryFilePath = getDictionaryFilePath();
        }

        Definitions definitions = getDictionaryDefinitions(dictionaryFilePath);

        ApplicationController applicationController = new ApplicationController();
        applicationController.start(outputFormatter, definitions);
    }

    private static OutputFormatter getModeInput() {
        System.out.println("Please type the mode with which you would like the run the application");
        System.out.println("Basic : Basic output with simple text formatting");
        System.out.println("Pretty : Pretty printed output in tabular format");

        Scanner reader = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String input = reader.next();
            if (input.equalsIgnoreCase("basic")) {
                return new BasicOutputFormatter();
            } else if (input.equalsIgnoreCase("pretty")) {
                return new PrettyOutputFormatter();
            } else {
                System.out.println("Could not recognise input, please enter either 'Pretty' or 'Basic'");
            }
        }
    }

    private static String getDictionaryFilePath() {
        System.out.println("Please enter the dictionary file path:");

        Scanner reader = new Scanner(System.in);
        System.out.print(">");
        return reader.next();
    }

    private static Definitions getDictionaryDefinitions(String dictionaryFilePath) {
        while (true) {
            try {
                return DictionaryParser.parseDictionaryFile(dictionaryFilePath);
            } catch (IOException e) {
                System.out.println("Unable to read dictionary file from the given path");
                dictionaryFilePath = getDictionaryFilePath();
            }
        }
    }

}
