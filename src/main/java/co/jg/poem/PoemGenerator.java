package co.jg.poem;

import co.jg.rules.Rule;
import co.jg.rules.reader.RuleReader;

public class PoemGenerator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        RuleReader reader = new RuleReader();
        Rule rule = reader.buildRules("src/test/resources/rules.txt");
        rule.processRule();
    }

}
