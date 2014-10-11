package co.jg.poem;

import java.util.List;

import co.jg.rules.Rule;
import co.jg.rules.reader.RuleReader;

public class PoemGenerator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        RuleReader reader = new RuleReader();
        Rule rule = reader.buildRules("src/test/resources/rules.txt");
        
    }

//    private static void printRules(List<Rule> rules) {
//        for (Rule rule : rules) {
//            System.out.println("Rule Name: " + rule.getRuleName());
//            for (Rule element : rule.getElements()) {
//                System.out.println("Element: " + element.getRuleName());
//                for (Rule part : element.getElements()) {
//                    System.out.println("Part: " + part.getRuleName());
//                }
//                for (String words : element.getWords()) {
//                    System.out.print(words + ", ");
//                }
//            }
//        }
//    }

}
