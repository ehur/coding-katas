package exercises;

public class TemperatureConverter {

    public static final double kelvinCelsiusBaseline = -273.15;
    static final String KELVIN = "k";
    static final String CELSIUS = "c";
    static final String FAHRENHEIT = "f";
    static final String KELVIN2FAHRENHEIT = "k2f";


    public Double celsiusToFahrenheit(Double temperature) {
        Double converted = (temperature * 9 / 5) + 32;
        return converted;
    }

    public Double kelvinToCelsius(Double temp) {
        return temp + kelvinCelsiusBaseline;
    }

    public Double kelvinToFahrenheit(Double temp) {
        return celsiusToFahrenheit(kelvinToCelsius(temp));
    }

    public Double convert(Double temp, String from, String to) {
        if(from.equals(KELVIN)) {
            if(to.equals(CELSIUS)) {

            }
            else if(to.equals(FAHRENHEIT)) {

            }
            else {
                return temp;
            }
        }
        else if(from.equals(CELSIUS)) {

        }
        else {

        }
        return 0.0;
    }
}
