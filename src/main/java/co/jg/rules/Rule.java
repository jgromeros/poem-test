package co.jg.rules;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private List<Rule> elements;
    private List<String> words;
    private String ruleName;

    public Rule() {
        elements = new ArrayList<Rule>();
        words = new ArrayList<String>();
    }

    public Rule(String ruleName) {
        elements = new ArrayList<Rule>();
        words = new ArrayList<String>();
        this.ruleName = ruleName;
    }

    public void processRule() {
        for (Rule element : getElements()) {
            element.processRule();
        }
    }

    public List<Rule> getElements() {
        return elements;
    }

    public void setElements(List<Rule> elements) {
        this.elements = elements;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

}
