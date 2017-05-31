package uk.ac.aston.dc2310.model;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * @author George Davies
 * @since 19/05/17.
 */
public class DefinitionsTest {

    private Definitions testDefinitions;

    @Before
    public void setup() {
        testDefinitions = createPopulatedTestDefinitions();
    }

    @Test
    public void getSingleDefinitionByTraditionalCharacters() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByTraditionalOrSimplified("選美");

        assertEquals(1, returnedDefinitions.size());
        assertEquals("選美", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("选美", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("xuan3 mei3", returnedDefinitions.get(0).getPinYin());
        assertEquals(Collections.singletonList("beauty contest"),
                returnedDefinitions.get(0).getEnglishEquivalents());
    }

    @Test
    public void getSingleDefinitionBySimplifiedCharacters() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByTraditionalOrSimplified("选美");

        assertEquals(1, returnedDefinitions.size());
        assertEquals("選美", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("选美", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("xuan3 mei3", returnedDefinitions.get(0).getPinYin());
        assertEquals(Collections.singletonList("beauty contest"),
                returnedDefinitions.get(0).getEnglishEquivalents());
    }

    @Test
    public void getSingleDefinitionByPinYin() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByPinYin("xiao1 hua4 mei2");

        assertEquals(1, returnedDefinitions.size());
        assertEquals("消化酶", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("消化酶", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("xiao1 hua4 mei2", returnedDefinitions.get(0).getPinYin());
        assertEquals(Collections.singletonList("digestive enzyme"),
                returnedDefinitions.get(0).getEnglishEquivalents());
    }

    @Test
    public void getMultipleDefinitionsByPinYin() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByPinYin("yi1 shi4");

        assertEquals(2, returnedDefinitions.size());
        assertEquals("一世", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("一世", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("yi1 shi4", returnedDefinitions.get(0).getPinYin());
        assertEquals(Arrays.asList("generation", "period of 30 years", "one's whole lifetime", "lifelong", "age", "era", "times", "the whole world", "the First (of numbered European kings)"),
                returnedDefinitions.get(0).getEnglishEquivalents());
        assertEquals("衣飾", returnedDefinitions.get(1).getTraditionalChinese());
        assertEquals("衣饰", returnedDefinitions.get(1).getSimplifiedChinese());
        assertEquals("yi1 shi4", returnedDefinitions.get(1).getPinYin());
        assertEquals(Collections.singletonList("clothes and ornaments"),
                returnedDefinitions.get(1).getEnglishEquivalents());
    }

    @Test
    public void getSingleDefinitionByTraditionalPrefix() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByTraditionalPrefix("杳然");

        assertEquals(1, returnedDefinitions.size());
        assertEquals("杳然", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("杳然", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("yao3 ran2", returnedDefinitions.get(0).getPinYin());
        assertEquals(Arrays.asList("quiet, silent, and lonely", "far and indistinct", "gone without a trace"),
                returnedDefinitions.get(0).getEnglishEquivalents());
    }

    @Test
    public void getMultipleDefinitionsByTraditionalPrefix() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByTraditionalPrefix("捷克");

        assertEquals(5, returnedDefinitions.size());
        assertEquals("捷克", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("捷克", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("Jie2 ke4", returnedDefinitions.get(0).getPinYin());
        assertEquals(Arrays.asList("Czech", "Czech Republic (from 1993)", "Czechia"),
                returnedDefinitions.get(0).getEnglishEquivalents());
        assertEquals("捷克人", returnedDefinitions.get(1).getTraditionalChinese());
        assertEquals("捷克人", returnedDefinitions.get(1).getSimplifiedChinese());
        assertEquals("Jie2 ke4 ren2", returnedDefinitions.get(1).getPinYin());
        assertEquals(Collections.singletonList("Czech person"),
                returnedDefinitions.get(1).getEnglishEquivalents());
        assertEquals("捷克共和國", returnedDefinitions.get(2).getTraditionalChinese());
        assertEquals("捷克共和国", returnedDefinitions.get(2).getSimplifiedChinese());
        assertEquals("Jie2 ke4 ren2", returnedDefinitions.get(2).getPinYin());
        assertEquals(Collections.singletonList("Czech Republic"),
                returnedDefinitions.get(2).getEnglishEquivalents());
        assertEquals("捷克斯洛伐克", returnedDefinitions.get(3).getTraditionalChinese());
        assertEquals("捷克斯洛伐克", returnedDefinitions.get(3).getSimplifiedChinese());
        assertEquals("Jie2 ke4 Si1 luo4 fa2 ke4", returnedDefinitions.get(3).getPinYin());
        assertEquals(Collections.singletonList("Republic of Czechoslovakia (1918-1992)"),
                returnedDefinitions.get(3).getEnglishEquivalents());
        assertEquals("捷克語", returnedDefinitions.get(4).getTraditionalChinese());
        assertEquals("捷克语", returnedDefinitions.get(4).getSimplifiedChinese());
        assertEquals("Jie2 ke4 yu3", returnedDefinitions.get(4).getPinYin());
        assertEquals(Collections.singletonList("Czech (language)"),
                returnedDefinitions.get(4).getEnglishEquivalents());
    }

    @Test
    public void getSingleDefinitionByEnglishEquivalent() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByEnglishEquivalent("material for clothing");

        assertEquals(1, returnedDefinitions.size());
        assertEquals("衣料", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("衣料", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("yi1 liao4", returnedDefinitions.get(0).getPinYin());
        assertEquals(Collections.singletonList("material for clothing"),
                returnedDefinitions.get(0).getEnglishEquivalents());
    }

    @Test
    public void getMultipleDefinitionByEnglishEquivalent() {
        List<Definition> returnedDefinitions = testDefinitions.getDefinitionsByEnglishEquivalent("beauty contest");

        assertEquals(4, returnedDefinitions.size());
        assertEquals("競秀", returnedDefinitions.get(0).getTraditionalChinese());
        assertEquals("竞秀", returnedDefinitions.get(0).getSimplifiedChinese());
        assertEquals("jing4 xiu4", returnedDefinitions.get(0).getPinYin());
        assertEquals(Arrays.asList("beauty contest", "vying to be the most beautiful"),
                returnedDefinitions.get(0).getEnglishEquivalents());
        assertEquals("競艷", returnedDefinitions.get(1).getTraditionalChinese());
        assertEquals("竞艳", returnedDefinitions.get(1).getSimplifiedChinese());
        assertEquals("jing4 yan4", returnedDefinitions.get(1).getPinYin());
        assertEquals(Arrays.asList("vying to be the most glamorous", "each more gorgeous than the other", "beauty contest"),
                returnedDefinitions.get(1).getEnglishEquivalents());
        assertEquals("選美", returnedDefinitions.get(2).getTraditionalChinese());
        assertEquals("选美", returnedDefinitions.get(2).getSimplifiedChinese());
        assertEquals("xuan3 mei3", returnedDefinitions.get(2).getPinYin());
        assertEquals(Collections.singletonList("beauty contest"),
                returnedDefinitions.get(2).getEnglishEquivalents());
        assertEquals("選美比賽", returnedDefinitions.get(3).getTraditionalChinese());
        assertEquals("选美比赛", returnedDefinitions.get(3).getSimplifiedChinese());
        assertEquals("xuan3 mei3 bi3 sai4", returnedDefinitions.get(3).getPinYin());
        assertEquals(Collections.singletonList("beauty contest"),
                returnedDefinitions.get(3).getEnglishEquivalents());
    }

    private Definitions createPopulatedTestDefinitions() {
        Definitions definitions = new Definitions();

        definitions.addDefinition(new Definition("一世", "一世", "yi1 shi4",
                new ArrayList<>(Arrays.asList("generation", "period of 30 years", "one's whole lifetime", "lifelong", "age", "era", "times", "the whole world", "the First (of numbered European kings)"))));
        definitions.addDefinition(new Definition("衣飾", "衣饰", "yi1 shi4",
                new ArrayList<>(Collections.singletonList("clothes and ornaments"))));
        definitions.addDefinition(new Definition("衣料", "衣料", "yi1 liao4",
                new ArrayList<>(Collections.singletonList("material for clothing"))));
        definitions.addDefinition(new Definition("消化酶", "消化酶", "xiao1 hua4 mei2",
                new ArrayList<>(Collections.singletonList("digestive enzyme"))));
        definitions.addDefinition(new Definition("杳然", "杳然", "yao3 ran2",
                new ArrayList<>(Arrays.asList("quiet, silent, and lonely", "far and indistinct", "gone without a trace"))));
        definitions.addDefinition(new Definition("捷克", "捷克", "Jie2 ke4",
                new ArrayList<>(Arrays.asList("Czech", "Czech Republic (from 1993)", "Czechia"))));
        definitions.addDefinition(new Definition("捷克人", "捷克人", "Jie2 ke4 ren2",
                new ArrayList<>(Collections.singletonList("Czech person"))));
        definitions.addDefinition(new Definition("捷克共和國", "捷克共和国", "Jie2 ke4 ren2",
                new ArrayList<>(Collections.singletonList("Czech Republic"))));
        definitions.addDefinition(new Definition("捷克斯洛伐克", "捷克斯洛伐克", "Jie2 ke4 Si1 luo4 fa2 ke4",
                new ArrayList<>(Collections.singletonList("Republic of Czechoslovakia (1918-1992)"))));
        definitions.addDefinition(new Definition("捷克語", "捷克语", "Jie2 ke4 yu3",
                new ArrayList<>(Collections.singletonList("Czech (language)"))));
        definitions.addDefinition(new Definition("競秀", "竞秀", "jing4 xiu4",
                new ArrayList<>(Arrays.asList("beauty contest", "vying to be the most beautiful"))));
        definitions.addDefinition(new Definition("競艷", "竞艳", "jing4 yan4",
                new ArrayList<>(Arrays.asList("vying to be the most glamorous", "each more gorgeous than the other", "beauty contest"))));
        definitions.addDefinition(new Definition("選美", "选美", "xuan3 mei3",
                new ArrayList<>(Collections.singletonList("beauty contest"))));
        definitions.addDefinition(new Definition("選美比賽", "选美比赛", "xuan3 mei3 bi3 sai4",
                new ArrayList<>(Collections.singletonList("beauty contest"))));

        return definitions;
    }

}
