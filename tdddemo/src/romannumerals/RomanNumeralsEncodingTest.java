package romannumerals;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RomanNumeralsEncodingTest {

    @Test //tells the jvm this method is a junit test so it knows how to run it
    public void explainAssert() {
        String one = "I";
        String five = "V";
        assertEquals("I",one); //(expected string, actual string)
        //assertEquals(five,one);
    }

    @Test
    public void shouldEncodeOne() {
        String result = RomanNumeralsUtil.encode(1);
        assertEquals("I", result);
    }

    @Test
         public void shouldEncodeTwo() {
        String result = RomanNumeralsUtil.encode(2);
        assertEquals("II", result);
    }

    @Test
    public void shouldEncodeThree() {
        String result = RomanNumeralsUtil.encode(3);
        assertEquals("III", result);
    }

    @Test
    public void shouldEncodeFour() {
        String result = RomanNumeralsUtil.encode(4);
        assertEquals("IV", result);
    }

    @Test
    public void shouldEncodeFive() {
        String result = RomanNumeralsUtil.encode(5);
        assertEquals("V", result);
    }
    @Test
    public void shouldEncodeSix() {
        String result = RomanNumeralsUtil.encode(6);
        assertEquals("VI", result);
    }

    @Test
    public void shouldEncodeSevenAndEight() {
        String result1 = RomanNumeralsUtil.encode(7);
        assertEquals("VII", result1);
        String result2 = RomanNumeralsUtil.encode(8);
        assertEquals("VIII", result2);
    }

    @Test
    public void shouldEncodeNine() {
        String result = RomanNumeralsUtil.encode(9);
        assertEquals("IX", result);
    }

    @Test
    public void shouldEncodeTen() {
        String result = RomanNumeralsUtil.encode(10);
        assertEquals("X", result);
    }

    @Test
    public void shouldEncodeEleven() {
        String result = RomanNumeralsUtil.encode(11);
        assertEquals("XI", result);
    }

    @Test
    public void shouldEncodeTwelveThruNineteen() {
        String result = RomanNumeralsUtil.encode(12);
        assertEquals("XII", result);
        result = RomanNumeralsUtil.encode(14);
        assertEquals("XIV", result);
        result = RomanNumeralsUtil.encode(15);
        assertEquals("XV", result);
        result = RomanNumeralsUtil.encode(19);
        assertEquals("XIX", result);
    }

    @Test
    public void shouldEncodeTwenty() {
        String result = RomanNumeralsUtil.encode(20);
        assertEquals("XX", result);
    }
    @Test
    public void shouldEncodeTwentyOne() {
        String result = RomanNumeralsUtil.encode(21);
        assertEquals("XXI", result);
    }
    @Test
    public void shouldEncodeTwentyNine() {
        String result = RomanNumeralsUtil.encode(29);
        assertEquals("XXIX", result);
    }
    @Test
    public void shouldEncodeThirty() {
        String result = RomanNumeralsUtil.encode(30);
        assertEquals("XXX", result);
    }
    @Test
    public void shouldEncodeThirtyNine() {
        String result = RomanNumeralsUtil.encode(39);
        assertEquals("XXXIX", result);
    }
    @Test
    public void shouldEncodeFourty() {
        String result = RomanNumeralsUtil.encode(40);
        assertEquals("XL", result);
    }
    @Test
    public void shouldEncodeFourtyOne() {
        String result = RomanNumeralsUtil.encode(41);
        assertEquals("XLI", result);
    }
    @Test
    public void shouldEncodeFourtyNine() {
        String result = RomanNumeralsUtil.encode(49);
        assertEquals("XLIX", result);
    }
    @Test
    public void shouldEncodeFourtyFour() {
        String result = RomanNumeralsUtil.encode(44);
        assertEquals("XLIV", result);
    }
    @Test
    public void shouldEncodeFifty() {
        String result = RomanNumeralsUtil.encode(50);
        assertEquals("L", result);
    }
    @Test
    public void shouldEncodeFiftyNine() {
        String result = RomanNumeralsUtil.encode(59);
        assertEquals("LIX", result);
    }
    @Test
    public void shouldEncodeNinety() {
        String result = RomanNumeralsUtil.encode(90);
        assertEquals("XC", result);
    }
    @Test
    public void shouldEncodeNinetyFive() {
        String result = RomanNumeralsUtil.encode(95);
        assertEquals("XCV", result);
    }

    @Test
    public void shouldEncodeOneHundredNinetyNine() {
        String result = RomanNumeralsUtil.encode(199);
        assertEquals("CXCIX", result);
    }

    @Test
    public void shouldEncodeTwoHundred() {
        String result = RomanNumeralsUtil.encode(200);
        assertEquals("CC", result);
    }
}
