package co.jg.poem.utils;

import co.jg.rules.Rule;
import co.jg.rules.Word;

/**
 * Class to maintain some proccessing methods to extract or use rules. Put here only methods which
 * do not have any "business logic"
 * @author Juan Gabriel Romero
 */
public class RulesUtil {

    private static final String WORDS_SUFFIX = "Words";

    private RulesUtil() {
    }

    /**
     * While reading the rule names are splitted with tokens "<" and ">". This method isolates the
     * rule name and returns it without those tokens.
     * The tokens must be at the beginning and at the end of the parameter
     * @param item
     * @return
     */
    public static String isolateRuleName(String item) {
        return item.substring(item.indexOf("<") + 1, item.indexOf(">"));
    }

    /**
     * Returns the innerRule for saving words corresponding to the rule in the parameter.
     * It is named as the outer name plus the suffix Words
     * @param outerRule
     * @return
     */
    public static Word createWordRule(Rule outerRule) {
        Word wordRule = (Word) outerRule.getInnerElementByName(outerRule.getRuleName()
                + WORDS_SUFFIX);
        if (wordRule == null) {
            wordRule = new Word(outerRule.getRuleName() + WORDS_SUFFIX);
            outerRule.getElements().add(wordRule);
        }
        return wordRule;
    }

}
