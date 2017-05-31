package uk.ac.aston.dc2310;

import uk.ac.aston.dc2310.controller.ApplicationController;
import uk.ac.aston.dc2310.formatter.BasicOutputFormatter;
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
        System.out.println("\nDC2310 Chinese English Translator\n");

        String dictionaryFilePath;
        if (args.length == 1) {
            dictionaryFilePath = args[0];
        } else {
            dictionaryFilePath = getDictionaryFilePath();
        }
        Definitions definitions = getDictionaryDefinitions(dictionaryFilePath);

        printUsageInstructions();

        ApplicationController applicationController = new ApplicationController(definitions, new BasicOutputFormatter());
        applicationController.start();
    }

    private static void printUsageInstructions() {
        System.out.println("Modes:");
        System.out.println("Chinese      : Enter a Chinese word in either traditional or simplified characters eg. '競秀'");
        System.out.println("PinYin       : Enter the PinYin eg. 'jing4 xiu4'");
        System.out.println("English      : Enter an english word or phrase eg. 'beauty contest'");
        System.out.println("Prefix       : Enter the prefix in traditional characters eg. '競'");

        System.out.println("\nCommands:");
        System.out.println("/mode [mode] : Set the current translation mode of the application");
        System.out.println("/stats       : Shows interesting statistics about the dictionary");
        System.out.println("/quit        : Shows interesting statistics about the dictionary");

        System.out.println();
    }

    private static String getDictionaryFilePath() {
        System.out.println("Please enter the dictionary file path:");

        Scanner reader = new Scanner(System.in);
        System.out.print(">");
        return reader.nextLine();
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
