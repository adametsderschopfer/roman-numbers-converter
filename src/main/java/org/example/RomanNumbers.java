package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumbers {
    private static final String romanLetters = "IVXLCDM";

    private static final RomanSet[] ROMAN_SETS = {
            new RomanSet("IV", 4),
            new RomanSet("IX", 9),
            new RomanSet("XL", 40),
            new RomanSet("XC", 90),
            new RomanSet("CD", 400),
            new RomanSet("CM", 900),
    };

    private static final RomanSet[] ROMAN_NUMBERS = {
            new RomanSet("I", 1),
            new RomanSet("V", 5),
            new RomanSet("X", 10),
            new RomanSet("L", 50),
            new RomanSet("C", 100),
            new RomanSet("D", 500),
            new RomanSet("M", 1000),
    };

    private static void validate(String[] romans) throws Exception {
        for (String romanNumber : romans) {
            if (!romanLetters.contains(romanNumber)) {
                throw new Exception("Incorrect value { " + romanNumber + " }");
            }
        }
    }

    public static int toInt(String s) throws Exception {
        String romanNumbers = s.toUpperCase();
        int result = 0;

        if (romanNumbers.length() == 0) {
            return 0;
        }

        validate(romanNumbers.split(""));

        for (RomanSet romanSet : ROMAN_SETS) {
            boolean hasFound = false;

            final String regex = romanSet.romanIdentifier;
            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(romanNumbers);

            while (matcher.find()) {
                if (!hasFound) {
                    hasFound = true;
                }

                result += romanSet.value;
            }

            if (hasFound) {
                romanNumbers = matcher.replaceAll("");
            }
        }

        if (romanNumbers.length() != 0) {
            for (String romanNumber : romanNumbers.split("")) {
                for (RomanSet foundRomanSet: ROMAN_NUMBERS) {
                    if (romanNumber.equals(foundRomanSet.romanIdentifier)) {
                        result += foundRomanSet.value;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
