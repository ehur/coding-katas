package exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureConverterTest {

    @Test
    public void toFahrenheitShouldWork() {
        Double expectedTemp = 32.0;
        TemperatureConverter converter = new TemperatureConverter();
        Double temp = converter.celsiusToFahrenheit(0.0);
        assertEquals(expectedTemp, temp);
    }

    @Test
    public void toFahrenheitShouldWorkForPositiveTemps() {
        Double expectedTemp = 50.0;
        TemperatureConverter converter = new TemperatureConverter();
        Double temp = converter.celsiusToFahrenheit(10.0);
        assertEquals(expectedTemp, temp);
    }

    @Test
    public void toFahrenheitShouldWorkForNegativeTemps() {
        Double expectedTemp = -40.0;
        TemperatureConverter converter = new TemperatureConverter();
        Double temp = converter.celsiusToFahrenheit(-40.0);
        assertEquals(expectedTemp, temp);
    }

    @Test
    public void shouldConvertFromKelvinToCelsius() {
        TemperatureConverter converter = new TemperatureConverter();
        Double expectedTemp = TemperatureConverter.kelvinCelsiusBaseline;

        Double temp = converter.kelvinToCelsius(0.0);
        assertEquals(expectedTemp, temp);
    }

    @Test
    public void shouldConvertFromKelvinToFahrenheit(){
        TemperatureConverter converter = new TemperatureConverter();
        Double temp = converter.kelvinToFahrenheit(0.0);
        Double expectedTemp = converter.celsiusToFahrenheit(TemperatureConverter.kelvinCelsiusBaseline);
        assertEquals(expectedTemp, temp);
    }

    @Test
    public void shouldConvertGivenFromTo() {
        TemperatureConverter converter = new TemperatureConverter();
        Double temp = converter.convert(10.0, TemperatureConverter.KELVIN, TemperatureConverter.CELSIUS);
        assertEquals(Double.valueOf(10.0), temp);
    }
}
