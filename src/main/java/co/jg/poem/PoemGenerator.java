package co.jg.poem;

import co.jg.rules.reader.RuleReader;

public class PoemGenerator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        RuleReader reader = new RuleReader();
        reader.buildRules("src/test/resources/rules.txt");
    }

}
