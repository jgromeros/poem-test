package co.jg.rules.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import co.jg.rules.Rule;
import co.jg.rules.SelectOne;
import co.jg.rules.Word;

/**
 * @author juano
 */
public class RuleReader {

    /**
     * Reads a file and creates a list of rules by processing each line as a new rule. This method
     * expects that the first rule defined in the file is the root rule. Finally, this method
     * returns the root rule.
     * @param fileName
     * @return
     */
    public Rule buildRules(String fileName) {
        List<Rule> rules = new ArrayList<Rule>();
        File file = new File(fileName);
        try {
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            for (String line : lines) {
                processRule(rules, line);
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return rules.get(0);
    }

    /**
     * Process one line to create one rule and adds it to the rules parameter.
     * 
     * @param rules
     * @param line
     */
    private void processRule(List<Rule> rules, String line) {
        String[] ruleParts = line.split(":");
        String ruleName = ruleParts[0].trim();
        String ruleDefinition = ruleParts[1].trim();
        String[] ruleDefItems = ruleDefinition.split(" ");
        Rule rule = obtainRule(rules, ruleName);
        for (String item : ruleDefItems) {
            if (item.contains("|")) {
                String[] orItems = item.split("\\|");
                for (String orItem : orItems) {
                    createInnerOrRule(rules, rule, orItem);
                }
            } else {
                createInnerRule(rules, rule, item);
            }
        }
    }

    /**
     * Searches for a rule by its name in the list of rules. If the name is not part of the list
     * of rules, a new rule is added and returned
     * @param rules
     * @param ruleName
     * @return
     */
    private Rule obtainRule(List<Rule> rules, String ruleName) {
        for (Rule rule : rules) {
            if (ruleName.equals(rule.getRuleName())) {
                return rule;
            }
        }
        Rule newRule = new Rule(ruleName);
        rules.add(newRule);
        return newRule;
    }

    /**
     * If the inner rule is part of an or operation it needs to be added to the list of elements
     * as part of another structure. That structure is a SelectOne rule.
     * @param rules
     * @param outerRule
     * @param item
     */
    private void createInnerOrRule(List<Rule> rules, Rule outerRule, String item) {
        if (item.contains("<") && item.contains(">") || item.contains("$")) {
            SelectOne selectRule = (SelectOne) outerRule.getInnerElementByName(outerRule
                    .getRuleName() + "Rules");
            if (selectRule == null) {
                selectRule = new SelectOne(outerRule.getRuleName() + "Rules");
                outerRule.getElements().add(selectRule);
            }
            if (!item.contains("$")) {
                selectRule.getElements().add(obtainRule(rules, isolateRuleName(item)));
            } else {
                Word specialRule = new Word(item);
                specialRule.getWords().add("$LINEBREAK".equals(item) ? "\n" : "");
                selectRule.getElements().add(specialRule);
            }
        }else {
            Word wordRule = (Word) outerRule.getInnerElementByName(outerRule.getRuleName()
                    + "Words");
            if (wordRule == null) {
                wordRule = new Word(outerRule.getRuleName() + "Words");
                outerRule.getElements().add(wordRule);
            }
            wordRule.getWords().add(item);
        }
    }

    /**
     * When the inner rule is not part of an or operation, it can be added by using this method.
     * @param rules
     * @param outerRule
     * @param item
     */
    private void createInnerRule(List<Rule> rules, Rule outerRule, String item) {
        if (item.contains("<") && item.contains(">")) {
            outerRule.getElements().add(obtainRule(rules, isolateRuleName(item)));
        } else {
            Word wordRule = (Word) outerRule.getInnerElementByName(outerRule.getRuleName()
                    + "Words");
            if (wordRule == null) {
                wordRule = new Word(outerRule.getRuleName() + "Words");
                outerRule.getElements().add(wordRule);
            }
            wordRule.getWords().add("$LINEBREAK".equals(item) ? "\n" : item);
        }
    }

    /**
     * While reading the rule names are splitted with tokens "<" and ">". This method isolates the
     * rule name and returns it without those tokens.
     * The tokens must be at the beginning and at the end of the parameter
     * @param item
     * @return
     */
    private String isolateRuleName(String item) {
        return item.substring(item.indexOf("<") + 1, item.indexOf(">"));
    }

}
