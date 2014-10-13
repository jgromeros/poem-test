package co.jg.rules;

import java.util.ArrayList;
import java.util.List;

import co.jg.exceptions.RuleException;

/**
 * Base class that represents rules defined for the creation of poems. Rules are identified by its
 * ruleName
 * @author Juan Gabriel Romero
 */
public class Rule {

    private List<Rule> elements;
    private List<String> words;
    private String ruleName;

    /**
     * Empty constructor. It instantiates the lists that compose a rule.
     */
    public Rule() {
        elements = new ArrayList<Rule>();
        words = new ArrayList<String>();
    }

    /**
     * Constructor that assigns the rule name. It instantiates the lists that compose a rule.
     */
    public Rule(String ruleName) {
        elements = new ArrayList<Rule>();
        words = new ArrayList<String>();
        this.ruleName = ruleName;
    }

    /**
     * Creates the poem by making recursive calls to its inner rules (rules that are part of the
     * definition of the rule). Since its implemented through recursive calls, the poem is
     * iteratively created by appending to the parameter.
     * @param poem
     * @throws RuleException 
     */
    public void processRule(StringBuilder poem) {
        if (getElements().isEmpty()) {
            throw new RuleException("The rule has no definition");
        }
        for (Rule element : getElements()) {
            element.processRule(poem);
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
