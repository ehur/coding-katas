package romannumerals;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RomanNumeralsEncodingTest {

    @Test
    public void explainAssert() {
        String one = "I";
        String five = "V";
        assertEquals("I",one);
//        assertEquals(five,one);
    }

    @Test
    public void shouldEncodeOne(){
        String expected = "I";
        String actual = RomanNumeralsUtil.encode(1);
        assertEquals(expected,actual);

    }

    @Test
    public void shouldEncodeTwoAndThree() {
        String expectedTwo = "II";

        String actualTwo = RomanNumeralsUtil.encode(2);

        assertEquals(expectedTwo, actualTwo);
    }

     @Test
     public void shouldEncodeFour() {
         String expected = "IV";
         String actual = RomanNumeralsUtil.encode(4);
         assertEquals(expected,actual);
     }


    @Test
    public void shouldEncodeFive() {
        String expected = "V";
        String actual = RomanNumeralsUtil.encode(5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeSix() {
        String expected = "VI";
        String actual = RomanNumeralsUtil.encode(6);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeSeven() {
        String expected = "VII";
        String actual = RomanNumeralsUtil.encode(7);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeNine() {
        String expected = "IX";
        String actual = RomanNumeralsUtil.encode(9);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeTen() {
        String expected = "X";
        String actual = RomanNumeralsUtil.encode(10);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeEleven() {
        String expected = "XI";
        String actual = RomanNumeralsUtil.encode(11);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeTwelve() {
        String expected = "XII";
        String actual = RomanNumeralsUtil.encode(12);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeThirteen() {
        String expected = "XIII";
        String actual = RomanNumeralsUtil.encode(13);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeFourteen() {
        String expected = "XIV";
        String actual = RomanNumeralsUtil.encode(14);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeFifteen() {
        String expected = "XV";
        String actual = RomanNumeralsUtil.encode(15);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeSixteen() {
        String expected = "XVI";
        String actual = RomanNumeralsUtil.encode(16);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeNineteen() {
        String expected = "XIX";
        String actual = RomanNumeralsUtil.encode(19);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeTwenty() {
        String expected = "XX";
        String actual = RomanNumeralsUtil.encode(20);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeThirty() {
        String expected = "XXX";
        String actual = RomanNumeralsUtil.encode(30);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeThirtyFive() {
        String expected = "XXXV";
        String actual = RomanNumeralsUtil.encode(35);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeFortyNine() {
        String expected = "XLIX";
        String actual = RomanNumeralsUtil.encode(49);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeFortyEight() {
        String expected = "XLVIII";
        String actual = RomanNumeralsUtil.encode(48);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeFiftyNine() {
        String expected = "LIX";
        String actual = RomanNumeralsUtil.encode(59);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeEightyEight() {
        String expected = "LXXXVIII";
        String actual = RomanNumeralsUtil.encode(88);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeNinetyNine() {
        String expected = "XCIX";
        String actual = RomanNumeralsUtil.encode(99);
        assertEquals(expected, actual);
    }

    public static String encode(Integer number) {
        StringBuffer result = new StringBuffer();

        if(number >= 90) {
            result.append("XC");
            number -= 90;
        }

        if (number >= 50) {
            result.append("L");
            number -= 50;
        }

        if(number >= 40) {
            result.append("XL");
            number -= 40;
        }

        Integer amountReplacedByXs = 0;
        for(int i = 10; i <= number; i += 10) {
            result.append("X");
            amountReplacedByXs += 10;
        }

        number -= amountReplacedByXs;

        if(number == 4) {
            result.append("IV");
            return result.toString();
        }

        if (number == 9) {
            result.append("IX");
            return result.toString();
        }

        if (number >= 5) {
            result.append("V");
            number -= 5;
        }

        for (int i=1;i<=number;i++) {
            result.append("I");
        }
        return result.toString();
    }
}
