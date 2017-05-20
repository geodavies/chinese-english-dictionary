package uk.ac.aston.dc2310.formatter;

import uk.ac.aston.dc2310.model.Definition;

import java.util.List;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public interface OutputFormatter {

    void printDefinitions(List<Definition> definitions);

}
