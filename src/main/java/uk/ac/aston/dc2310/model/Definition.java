package uk.ac.aston.dc2310.model;

import java.util.Set;

/**
 * @author George Davies
 * @since 19/05/17.
 */
public class Definition {

    private String traditionalChinese;

    private String simplifiedChinese;

    private Set<String> englishTranslations;

    public Definition(String traditionalChinese, String simplifiedChinese, Set<String> englishTranslations) {
        this.traditionalChinese = traditionalChinese;
        this.simplifiedChinese = simplifiedChinese;
        this.englishTranslations = englishTranslations;
    }

    public String getTraditionalChinese() {
        return traditionalChinese;
    }

    public String getSimplifiedChinese() {
        return simplifiedChinese;
    }

    public Set<String> getEnglishTranslations() {
        return englishTranslations;
    }

}
