package com.alexandreamyot.yose.primes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.alexandreamyot.yose.primes.Scribe.arabicToRoman;
import static com.alexandreamyot.yose.primes.Scribe.romanToArabic;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class ScribeTest {

    private final String roman;
    private final int arabic;

    public ScribeTest(String roman, int arabic) {
        this.roman = roman;
        this.arabic = arabic;
    }

    @Parameterized.Parameters(name="{0} translates to {1}")
    public static Iterable<Object[]> data() {
        return asList(new Object[][] {
                {"I", 1},
                {"II", 2},
                {"III", 3},
                {"IV", 4},
                {"V", 5},
                {"IX", 9},
                {"X", 10},
                {"XXIX", 29},
                {"CDXLIV", 444},
                {"MCMXCVIII", 1998},
                {"MMDCCLI", 2751},
                {"MMMCMXCIX", 3999}
        });
    }

    @Test
    public void romanTranslatesTo() {
        assertThat(romanToArabic(roman), equalTo(arabic));
    }

    @Test
    public void arabicTranslatesTo() {
        assertThat(arabicToRoman(arabic), equalTo(roman));
    }


}