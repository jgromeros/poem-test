package co.jg.rules;

import java.util.Random;

/**
 * Subclass of rule to manage the cases in which there is the need to select one word to add to
 * the poem.
 * It Randomly selects one word from the word list and appends it to the poem.
 * @author Juan Gabriel Romero
 */
public class Word extends Rule {

    /**
     * Constructor that sets the rule name.
     * @param ruleName
     */
    public Word(String ruleName) {
        super(ruleName);
    }

    /**
     * Randomly selects one of the words in the word list and appends it to the output.
     */
    public void processRule(StringBuilder poem) {
        Random randomNumber = new Random();
        String word = getWords().get(randomNumber.nextInt(getWords().size()));
        poem.append("\n".equals(word) ? word : word + " ");
    }

}
