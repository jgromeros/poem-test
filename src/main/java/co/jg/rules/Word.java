package co.jg.rules;

import java.util.Random;

public class Word extends Rule {

    public Word(String ruleName) {
        super(ruleName);
    }

    public void processRule() {
        Random randomNumber = new Random();
        String word = getWords().get(randomNumber.nextInt(getWords().size()));
        System.out.print("\n".equals(word) ? word : word + " ");
    }

}
