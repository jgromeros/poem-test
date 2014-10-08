package co.jg.rules.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import co.jg.rules.Rule;

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
                    if (orItem.contains("<") && orItem.contains(">")) {
                        rule.getElements().add(new Rule(orItem.substring(orItem.indexOf("<") + 1,
                                        orItem.indexOf(">"))));
                    } else {
                        rule.getWords().add(orItem);
                    }
                }
            } else {
                if (item.contains("<") && item.contains(">")) {
                    rule.getElements().add(new Rule(item.substring(item.indexOf("<") + 1,
                                    item.indexOf(">"))));
                } else {
                    rule.getWords().add(item);
                }
            }
        }
        return rule;
    }

}
