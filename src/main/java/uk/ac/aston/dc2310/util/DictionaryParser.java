package uk.ac.aston.dc2310.util;

import uk.ac.aston.dc2310.model.Definition;
import uk.ac.aston.dc2310.model.Definitions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author George Davies
 * @since 19/05/17.
 */
public class DictionaryParser {

    public static Definitions parseDictionaryFile(String filePath) throws IOException {
        Definitions definitions = new Definitions();

        Files.lines(Paths.get(filePath))
                .map(dictionaryLineToDefinition)
                .filter(Objects::nonNull)
                .forEach(definitions::addDefinition);

        return definitions;
    }

    private static Function<String, Definition> dictionaryLineToDefinition = line -> {
        Pattern linePattern = Pattern.compile("^(.+) (.+) \\[(.+)\\] \\/(.+)\\/$");
        Matcher matcher = linePattern.matcher(line);

        if (matcher.matches()) {
            String[] englishEquivalents = matcher.group(4).split("/");
            return new Definition(matcher.group(1), matcher.group(2), matcher.group(3), Arrays.asList(englishEquivalents));
        }

        return null;
    };

}
