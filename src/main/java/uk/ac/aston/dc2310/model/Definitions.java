package uk.ac.aston.dc2310.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author George Davies
 * @since 19/05/17.
 */
public class Definitions {

    private List<Definition> definitions;

    public Definitions() {
        definitions = new ArrayList<>();
    }

    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }

    public List<Definition> getDefinitionsByTraditionalOrSimplified(String chinese) {
        return this.definitions.stream()
                .filter(definition -> definition.getTraditionalChinese().equals(chinese) || definition.getSimplifiedChinese().equals(chinese))
                .collect(Collectors.toList());
    }

    public List<Definition> getDefinitionsByTraditionalPrefix(String prefix) {
        return this.definitions.stream()
                .filter(definition -> definition.getTraditionalChinese().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public List<Definition> getDefinitionsByPinYin(String pinYin) {
        return this.definitions.stream()
                .filter(definition -> definition.getPinYin().equalsIgnoreCase(pinYin))
                .collect(Collectors.toList());
    }

    public List<Definition> getDefinitionsByEnglishEquivalent(String english) {
        return this.definitions.stream()
                .filter(definition -> {
                    for (String englishEquivalent : definition.getEnglishEquivalents()) {
                        if (englishEquivalent.equalsIgnoreCase(english)) return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public List<Definition> getDefinitionsAsList() {
        return definitions;
    }

}
