package com.alexandreamyot.yose.primes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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
        int acc = 0;

        if (input.isEmpty()) {
            return acc;
        }

        for (Entry<String, Integer> entry : ROMAN_DICTIONARY.entrySet()) {
            if (input.startsWith(entry.getKey())) {
                acc += entry.getValue() + romanToArabic(input.substring(entry.getKey().length()));
                break;
            }
        }

        return acc;

    }

    public static String arabicToRoman(int number) {
        String acc = "";

        if (number < 1) {
            return acc;
        }

        for (Entry<String, Integer> entry : ROMAN_DICTIONARY.entrySet()) {
            if (number >= entry.getValue()) {
                acc += entry.getKey() + arabicToRoman(number - entry.getValue());
                break;
            }
        }

        return acc;

    }
}
