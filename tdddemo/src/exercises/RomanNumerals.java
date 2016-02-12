package exercises;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RomanNumerals {

    @Test
    public void explainingAsserts() {
        String the="the";
        assertEquals("Them",the);
    }

    @Test
    public void shouldEncodeOne(){
        Integer one = 1;
        String expectedResult = "I";

        //when
        String actualResult = RomanNumerals.encode(one);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldEncodeTwoAndThree() {
        Integer two = 2;
        String expectedResult = "II";

        //when
        String actualResult = RomanNumerals.encode(two);

        //then
        assertEquals(expectedResult, actualResult);
        actualResult = RomanNumerals.encode(3);

        assertEquals("III", actualResult);
    }

    @Test
    public void shouldReturnIV(){
        String actualResult = RomanNumerals.encode(4);
        assertEquals("IV",actualResult);
    }

    @Test
    public void shouldReturnV(){
        String actualResult = RomanNumerals.encode(5);
        assertEquals("V",actualResult);
    }

    @Test
    public void shouldReturnSixSevenEight(){
        String actualResult = RomanNumerals.encode(6);
        assertEquals("VI",actualResult);
        actualResult = RomanNumerals.encode(7);
        assertEquals("VII",actualResult);
        actualResult = RomanNumerals.encode(8);
        assertEquals("VIII",actualResult);
    }

    @Test
    public void shouldReturnIX(){
        String actualResult = RomanNumerals.encode(9);
        assertEquals("IX",actualResult);
        actualResult = RomanNumerals.encode(10);
        assertEquals("X",actualResult);
    }

    @Test
    public void shouldReturnTeensCorrectly() {
        String actualResult = RomanNumerals.encode(11);
        assertEquals("XI",actualResult);
        actualResult = RomanNumerals.encode(14);
        assertEquals("XIV",actualResult);
        actualResult = RomanNumerals.encode(15);
        assertEquals("XV",actualResult);
        actualResult = RomanNumerals.encode(17);
        assertEquals("XVII",actualResult);
        actualResult = RomanNumerals.encode(19);
        assertEquals("XIX",actualResult);
    }

    @Test
    public void shouldXXToXXXIX(){
        String actualResult = RomanNumerals.encode(20);
        assertEquals("XX",actualResult);
        actualResult = RomanNumerals.encode(30);
        assertEquals("XXX",actualResult);
        actualResult = RomanNumerals.encode(22);
        assertEquals("XXII",actualResult);
        actualResult = RomanNumerals.encode(24);
        assertEquals("XXIV",actualResult);
        actualResult = RomanNumerals.encode(39);
        assertEquals("XXXIX",actualResult);
    }

    @Test
    public void shouldHandleXL() {
        String actualResult = RomanNumerals.encode(40);
        assertEquals("XL", actualResult);
    }

    @Test
    public void shouldHandleFortyOne(){
        String actualResult = RomanNumerals.encode(41);
        assertEquals("XLI", actualResult);
    }

    private static String encode(Integer number) {
        StringBuffer result = new StringBuffer();


        if (number >= 40) {
            result.append("XL");
            number = number - 40;
        }

        while(number >= 10) {
            result.append('X');
            number = number - 10;
        }

        if(number == 4) {
            result.append("IV");
            return result.toString();
        }

        if (number == 9) {
            result.append("IX");
            return result.toString();
        }

        if (number >= 5 ) {
            result.append("V");
        }

        number = number - 5;

        for (int i=0;i<number;i++){
            result.append("I");
        }
        return result.toString();
    }
}
