package uk.ac.aston.dc2310.model;

import java.util.List;

/**
 * @author George Davies
 * @since 19/05/17.
 */
public class Definition {

    private String traditionalChinese;

    private String simplifiedChinese;

    private String pinYin;

    private List<String> englishEquivalents;

    public Definition(String traditionalChinese, String simplifiedChinese, String pinYin, List<String> englishEquivalents) {
        this.traditionalChinese = traditionalChinese;
        this.simplifiedChinese = simplifiedChinese;
        this.pinYin = pinYin;
        this.englishEquivalents = englishEquivalents;
    }

    public String getTraditionalChinese() {
        return traditionalChinese;
    }

    public String getSimplifiedChinese() {
        return simplifiedChinese;
    }

    public String getPinYin() {
        return pinYin;
    }

    public List<String> getEnglishEquivalents() {
        return englishEquivalents;
    }
}
