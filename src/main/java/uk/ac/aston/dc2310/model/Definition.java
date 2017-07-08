package uk.ac.aston.dc2310.model;

import java.util.List;

/**
 * This class represents a Dictionary Definition (or entry) which has the following fields:
 * - Traditional Chinese
 * - Simplified Chinese
 * - PinYin
 * - English Equivalents
 *
 * It has a toString method which pretty prints its content.
 *
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("=== Definition ===");
        stringBuilder.append("\nTraditional Chinese : ").append(traditionalChinese);
        stringBuilder.append("\nSimplified Chinese  : ").append(simplifiedChinese);
        stringBuilder.append("\nPinYin              : ").append(pinYin);

        stringBuilder.append("\nEnglish Equivalents : ");
        boolean first = true;
        for (String englishEquivalent : englishEquivalents) {
            if (first) {
                stringBuilder.append(englishEquivalent);
                first = false;
            } else {
                stringBuilder.append("\n                      ").append(englishEquivalent);
            }
        }

        return stringBuilder.toString();
    }

}
