package co.jg.poem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import co.jg.exceptions.RuleException;
import co.jg.rules.Rule;
import co.jg.rules.SelectOne;
import co.jg.rules.Word;

public class PoemGeneratorTest {

    private Rule poemRule;
    private Rule noun = new Rule("noun");
    private Rule preposition = new Rule("preposition");
    private Rule verb = new Rule("verb");
    private Rule end = new Word("end");

    private void nounDefinition() {
        Rule nounWords = new Word("nounWords");
        noun.getElements().add(nounWords);
        nounWords.getWords().add("heart");
        nounWords.getWords().add("sun");
        nounWords.getWords().add("moon");
        nounWords.getWords().add("thunder");
        nounWords.getWords().add("fire");
        Rule nounRules = new SelectOne("nounRules");
        nounRules.getElements().add(verb);
        nounRules.getElements().add(preposition);
        nounRules.getElements().add(end);
        noun.getElements().add(nounRules);
    }

    private void prepositionDefinition() {
        Rule prepWords = new Word("prepWords");
        preposition.getElements().add(prepWords);
        prepWords.getWords().add("above");
        prepWords.getWords().add("across");
        prepWords.getWords().add("against");
        prepWords.getWords().add("along");
        Rule prepRules = new SelectOne("prepRules");
        prepRules.getElements().add(noun);
        prepRules.getElements().add(verb);
        preposition.getElements().add(prepRules);
    }

    private void verbDefinition() {
        Rule verbWords = new Word("verbWords");
        verb.getElements().add(verbWords);
        verbWords.getWords().add("runs");
        verbWords.getWords().add("walks");
        verbWords.getWords().add("stands");
        verbWords.getWords().add("climbs");
        Rule verbRules = new SelectOne("verbRules");
        verbRules.getElements().add(noun);
        verbRules.getElements().add(end);
        verb.getElements().add(verbRules);
    }

    private void endRule() {
        end.getWords().add("\r\n");
    }

    @Before
    public void setUp() throws Exception {
        nounDefinition();
        prepositionDefinition();
        verbDefinition();
        endRule();
    }

    @Test
    public void testGeneration() {
        poemRule = new Rule("poem");
        Rule line = new Rule("line");
        poemRule.getElements().add(line);
        poemRule.getElements().add(line);
        SelectOne line1 = new SelectOne("lineRules");
        line1.getElements().add(noun);
        line1.getElements().add(preposition);
        line1.getElements().add(verb);
        line.getElements().add(line1);
        Word word = new Word("lineBreak");
        word.getWords().add("\n");
        line.getElements().add(word);
        StringBuilder poem = new StringBuilder();
        poemRule.processRule(poem);
        assertFalse(poem.toString().isEmpty());
    }

    @Test
    public void testGenerationWrongRules() {
        poemRule = new Rule("poem");
        Rule line = new Rule("line");
        poemRule.getElements().add(line);
        poemRule.getElements().add(line);
        SelectOne line1 = new SelectOne("lineRules");
        line1.getElements().add(new Rule("wrong-rule"));
        line.getElements().add(line1);
        Word word = new Word("lineBreak");
        word.getWords().add("\n");
        line.getElements().add(word);
        StringBuilder poem = new StringBuilder();
        try {
            poemRule.processRule(poem);
            fail("Expected exception not thrown");
        } catch (RuleException e) {
            assertEquals("The rule has no definition", e.getMessage());
        }
    }

}
