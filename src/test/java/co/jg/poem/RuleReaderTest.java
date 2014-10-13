package co.jg.poem;

import static org.junit.Assert.*;

import org.junit.Test;

import co.jg.rules.Rule;
import co.jg.rules.reader.RuleReader;

public class RuleReaderTest {

    private RuleReader ruleReader;

    @Test
    public void testBuildRules() {
        ruleReader = new RuleReader();
        Rule poemRule = ruleReader.buildRules("src/test/resources/rules.txt");
        assertEquals(5, poemRule.getElements().size());
    }

    @Test
    public void testBuildWrongRules() {
        ruleReader = new RuleReader();
        Rule poemRule = ruleReader.buildRules("src/test/resources/wrong-rules.txt");
        assertEquals(2, poemRule.getElements().size());
    }

}
