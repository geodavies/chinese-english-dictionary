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
        System.out.println("Starting Chinese English Dictionary Application");

        String dictionaryFilePath;
        if (args.length == 1) {
            dictionaryFilePath = args[0];
        } else {
            dictionaryFilePath = getDictionaryFilePath();
        }
        Definitions definitions = getDictionaryDefinitions(dictionaryFilePath);

        ApplicationController applicationController = new ApplicationController();
        applicationController.start(new BasicOutputFormatter(), definitions);
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
