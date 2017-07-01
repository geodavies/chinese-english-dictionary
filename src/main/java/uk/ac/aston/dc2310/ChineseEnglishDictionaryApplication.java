package uk.ac.aston.dc2310;

import uk.ac.aston.dc2310.controller.DefaultCEDictController;
import uk.ac.aston.dc2310.model.Dictionary;
import uk.ac.aston.dc2310.util.DictionaryParser;
import uk.ac.aston.dc2310.view.CEDictUI;

import java.io.IOException;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public class ChineseEnglishDictionaryApplication {

    public static void main(String[] args) throws IOException {
        System.out.println("\nDC2310 Chinese English Dictionary\n");

        Dictionary dictionary = DictionaryParser.parseDictionaryFile("src/main/resources/cedict_ts.u8");
        dictionary.index();

        new CEDictUI(new DefaultCEDictController(dictionary));
    }

}
