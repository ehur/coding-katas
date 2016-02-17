package romannumerals;

public class RomanNumeralsUtil {
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
