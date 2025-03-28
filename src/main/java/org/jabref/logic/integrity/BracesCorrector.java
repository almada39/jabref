package org.jabref.logic.integrity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BracesCorrector {

    private static final Pattern PATTERN_ESCAPED_CURLY_BRACES = Pattern.compile("(\\\\\\{)|(\\\\\\})");

    public static String apply(String input) {
        if (input == null) {
            return null;
        } else {
            Matcher matcher = PATTERN_ESCAPED_CURLY_BRACES.matcher(input);
            StringBuilder addedBraces = new StringBuilder(input);
            String c = matcher.replaceAll("");

            long diff = c.chars().filter(ch -> ch == '{').count() - c.chars().filter(ch -> ch == '}').count();
            while (diff != 0) {
                if (diff < 0) {
                    addedBraces.insert(0, "{");
                    diff++;
                } else {
                    addedBraces.append("}");
                    diff--;
                }
            }
            return addedBraces.toString();
        }
    }
}
