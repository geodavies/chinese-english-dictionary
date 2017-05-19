package uk.ac.aston.dc2310.model;

import java.util.List;

/**
 * @author George Davies
 * @since 19/05/17.
 */
public class Definitions {

    private List<Definition> definitions;

    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }

    public Definition getDefinitionByPinYin(String pinYin) {
        // TODO: Implement me
        return null;
    }

    public Definition getDefinitionsByEnglish(String english) {
        // TODO: Implement me
        return null;
    }

    public Definition getDefinitionByTraditionalPrefix(String prefix) {
        // TODO: Implement me
        return null;
    }

}
