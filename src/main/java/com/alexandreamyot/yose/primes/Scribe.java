package com.alexandreamyot.yose.primes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.Integer.valueOf;

public class Scribe {

    public static Integer romanToArabic(String input) {
        Integer acc = valueOf(0);

        for (Entry<String, Integer> entry : RomanDictionnary.toArabic().entrySet()) {
            String roman = entry.getKey();
            while (input.startsWith(roman)) {
                acc += entry.getValue();
                input = input.substring(roman.length());
            }
        }

        return acc;
    }

    public static String arabicToRoman(int number) {
        String acc = "";

        for (Entry<Integer, String> entry : RomanDictionnary.fromArabic().entrySet()) {
            int arabic = entry.getKey();
            while (number >= arabic) {
                acc += entry.getValue();
                number -= arabic;
            }
        }

        return acc;
    }

    private static class RomanDictionnary {
        static Map<String, Integer> DICTIONARY = new LinkedHashMap<String, Integer>() {{
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

        static Map<String, Integer> toArabic() {
            return DICTIONARY;
        }

        static Map<Integer, String> fromArabic() {
            Map<Integer, String> arabicToRoman = new LinkedHashMap<>();
            for (Entry<String, Integer> entry : DICTIONARY.entrySet()) {
                arabicToRoman.put(entry.getValue(), entry.getKey());
            }
            return arabicToRoman;

        }
    }
}
