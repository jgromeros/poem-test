package co.jg.rules;

import java.util.Random;

/**
 * Subclass of rule to manage the cases in which there is the need to select of one rule to apply.
 * It Randomly selects one of the rules in the elements list and proccesses it.
 * @author Juan Gabriel Romero
 */
public class SelectOne extends Rule {

    /**
     * Constructor that sets the rule name.
     * @param ruleName
     */
    public SelectOne(String ruleName) {
        super(ruleName);
    }

    /**
     * Randomly selects one of the rule in the elements list and proccesses it.
     */
    public void processRule(StringBuilder poem) {
        Random randomNumber = new Random();
        Rule elementRule = getElements().get(randomNumber.nextInt(getElements().size()));
        elementRule.processRule(poem);
    }

}
