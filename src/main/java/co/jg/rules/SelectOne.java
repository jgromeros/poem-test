package co.jg.rules;

import java.util.Random;

public class SelectOne extends Rule {

    public SelectOne(String ruleName) {
        super(ruleName);
    }

    public void processRule() {
        Random randomNumber = new Random();
        Rule elementRule = getElements().get(randomNumber.nextInt(getElements().size()));
        elementRule.processRule();
    }

}
