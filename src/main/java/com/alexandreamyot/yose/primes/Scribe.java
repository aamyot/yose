package com.alexandreamyot.yose.primes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Scribe {

    static Map<String, Integer> ROMAN_DICTIONARY = new LinkedHashMap<String, Integer>() {{
        put("M", 1000);
        put("CM", 900);
        put("D", 500);
        put("CD", 400);
        put("C", 100);
        put("XC", 90);
        put("L", 50);
        put("XL", 40);
        put("X", 10);
        put("IX", 9);
        put("V", 5);
        put("IV", 4);
        put("I", 1);
    }};

    public static int romanToArabic(String input) {
        return ROMAN_DICTIONARY.entrySet().stream()
                               .filter(entry -> input.startsWith(entry.getKey()))
                               .map(entry -> entry.getValue() + romanToArabic(input.substring(entry.getKey().length())))
                               .findFirst().orElse(0);

    }

    public static String arabicToRoman(int number) {
        return ROMAN_DICTIONARY.entrySet().stream()
                               .filter(entry -> number >= entry.getValue())
                               .map(entry -> entry.getKey() + arabicToRoman(number - entry.getValue()))
                               .findFirst().orElse("");
    }
}
