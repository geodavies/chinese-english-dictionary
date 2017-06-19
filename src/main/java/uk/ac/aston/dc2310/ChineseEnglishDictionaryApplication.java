package uk.ac.aston.dc2310;

import uk.ac.aston.dc2310.controller.CEDictController;
import uk.ac.aston.dc2310.controller.DefaultCEDictController;
import uk.ac.aston.dc2310.model.Dictionary;
import uk.ac.aston.dc2310.util.DictionaryParser;
import uk.ac.aston.dc2310.view.CEDictUI;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public class ChineseEnglishDictionaryApplication {

    public static void main(String[] args) {
        System.out.println("\nDC2310 Chinese English Dictionary\n");

        String dictionaryFilePath;
        if (args.length == 1) {
            dictionaryFilePath = args[0];
        } else {
            dictionaryFilePath = getDictionaryFilePath();
        }
        Dictionary dictionary = getDictionaryDefinitions(dictionaryFilePath);
        dictionary.index();

        CEDictController cEDictController = new DefaultCEDictController(dictionary);
        CEDictUI cEDictUI = new CEDictUI(cEDictController);
    }

    private static String getDictionaryFilePath() {
        System.out.println("Please enter the dictionary file path:");
        Scanner reader = new Scanner(System.in);
        System.out.print(">");
        return reader.nextLine();
    }

    private static Dictionary getDictionaryDefinitions(String dictionaryFilePath) {
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
