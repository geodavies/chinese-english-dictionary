package uk.ac.aston.dc2310.util;

import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles the parsing of Dictionary file into a Dictionary with Definitions.
 *
 * @author George Davies
 * @since 19/05/17.
 */
public class DictionaryParser {

    /**
     * Reads the file at the given filePath and converts each line into a Definition.
     * All definitions are added to a Dictionary which is returned.
     *
     * @param filePath the filePath of the file to parse
     * @return a Dictionary containing the Definitions
     * @throws IOException an error occurred
     */
    public static Dictionary parseDictionaryFile(String filePath) throws IOException {
        Dictionary dictionary = new Dictionary();

        Files.lines(Paths.get(filePath))
                .map(dictionaryLineToDefinition) // Convert line to Definition
                .filter(Objects::nonNull) // Remove if null
                .forEach(dictionary::addDefinition); // Add to dictionary

        return dictionary;
    }

    /**
     * A function which converts a String (Dictionary entry) to a Definition.
     */
    private static Function<String, Definition> dictionaryLineToDefinition = line -> {
        // Create regex matcher with pattern to match a dictionary entry
        // eg. 天黑 天黑 [tian1 hei1] /to get dark/dusk/
        Pattern linePattern = Pattern.compile("^(.+) (.+) \\[(.+)] /(.+)/$");
        Matcher matcher = linePattern.matcher(line);

        if (matcher.matches()) {
            String[] englishEquivalents = matcher.group(4).split("/"); // Split all english equivalents by '/'
            // Return Definition with the matched groups
            return new Definition(matcher.group(1), matcher.group(2), matcher.group(3), Arrays.asList(englishEquivalents));
        } else {
            return null;
        }
    };

}
