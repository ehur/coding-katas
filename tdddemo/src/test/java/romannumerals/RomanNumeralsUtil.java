package romannumerals;

public class RomanNumeralsUtil {

    public static String encode(int number) {

        StringBuffer result = new StringBuffer();

        Integer tenCounter=0;

        if(number >= 200){
            result.append("CC");
            number -= 200;
        }

        if(number >= 100){
            result.append("C");
            number -= 100;
        }

        if(number >= 90){
            result.append("XC");
            number -= 90;
        }

        if(number >= 50){
            result.append("L");
            number -= 50;
        }

        if(number >= 40){
            result.append("XL");
            number -= 40;
        }
        for (int i=10;i <= number;i+=10 ){
            result.append("X");
            tenCounter +=10;
        }

        number = number - tenCounter;

        if (number == 9) {
            result.append("IX");
            return result.toString();
        }

        if (number >= 5) {
            result.append("V");
            number -= 5;
        }

        if (number == 4) {
            result.append("IV");
            return result.toString();
        }

        for (int i=1; i<= number; i++) {
            result.append("I");
        }
            return result.toString();
    }


}
