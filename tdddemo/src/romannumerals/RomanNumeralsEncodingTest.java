package romannumerals;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RomanNumeralsEncodingTest {

    @Test //tells the jvm this method is a junit test so it knows how to run it
    public void explainAssert() {
        String one = "I";
        String five = "V";
        assertEquals("I",one); //(expected string, actual string)
        assertEquals(five,one);
    }

    @Test
    public void shouldWorkForOne(){
        String result = RomanNumeralsUtil.encode(1);
        assertEquals("I",result);
    }

    @Test
    public void shouldWorkForTwo() {
        String result = RomanNumeralsUtil.encode(2);
        assertEquals("II", result);
    }
}
