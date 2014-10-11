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

    /**
     * Finds in the list of elements one element with the ruleName equals to the one in the
     * parameter. If it's found, it's returned. Otherwise returns null.
     * @param innerName
     * @return
     */
    public Rule getInnerElementByName(String innerName) {
        for (Rule element : getElements()) {
            if (innerName.equals(element.getRuleName())) {
                return element;
            }
        }
        return null;
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
