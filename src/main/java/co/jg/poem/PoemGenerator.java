package co.jg.poem;

import co.jg.rules.Rule;
import co.jg.rules.reader.RuleReader;

/**
 * Main class for the generation of poems. The first parameter (args[0]) is the location of the
 * file in which there are the rules definitions for the generation.
 * @author Juan Gabriel Romero
 */
public class PoemGenerator {

    /**
     * Since the only method of this class is static. This class should not be instantiated.
     */
    private PoemGenerator() {
    }

    /**
     * Main method. Executes the application args[0] is the location of the file that describe the
     * rules for the poem generation.
     * @param args
     */
    public static void main(String[] args) {
        RuleReader reader = new RuleReader();
        Rule rule = reader.buildRules(args[0]);
        StringBuilder poem = new StringBuilder();
        rule.processRule(poem);
        System.out.println(poem.toString());
    }

}
