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

    public Rule buildRules(String fileName) {
        List<Rule> rules = new ArrayList<Rule>();
        File file = new File(fileName);
        try {
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            for (String line : lines) {
                rules.add(processRule(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Process one line to create one rule and returns it.
     * 
     * @param rules
     * @param line
     */
    private Rule processRule(String line) {
        String[] ruleParts = line.split(":");
        String ruleName = ruleParts[0].trim();
        String ruleDefinition = ruleParts[1].trim();
        String[] ruleDefItems = ruleDefinition.split(" ");
        Rule rule = new Rule(ruleName);
        for (String item : ruleDefItems) {
            if (item.contains("|")) {
                String[] orItems = item.split("\\|");
                for (String orItem : orItems) {
                    createInnerOrRule(rule, orItem);
                }
            } else {
                createInnerRule(rule, item);
            }
        }
        return rule;
    }

    private void createInnerOrRule(Rule outerRule, String item) {
        if (item.contains("<") && item.contains(">")) {
            SelectOne selectRule = (SelectOne) outerRule.getInnerElementByName(outerRule
                    .getRuleName() + "Rules");
            if (selectRule == null) {
                selectRule = new SelectOne(outerRule.getRuleName() + "Rules");
                outerRule.getElements().add(selectRule);
            }
            selectRule.getElements().add(new Rule(item.substring(item.indexOf("<") + 1,
                            item.indexOf(">"))));
        } else {
            Word wordRule = (Word) outerRule.getInnerElementByName(outerRule
                    .getRuleName() + "Words");
            if (wordRule == null) {
                wordRule = new Word(outerRule.getRuleName() + "Words");
                outerRule.getElements().add(wordRule);
            }
            wordRule.getWords().add(item);
        }
    }

    private void createInnerRule(Rule outerRule, String item) {
        if (item.contains("<") && item.contains(">")) {
            outerRule.getElements().add(new Rule(item.substring(item.indexOf("<") + 1,
                            item.indexOf(">"))));
        } else {
            outerRule.getWords().add(item);
        }
    }

}
