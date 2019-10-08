package temperature;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    @Test
    public void pickTheOscarWinner() {
        Map<Integer,String> nominees = new HashMap<Integer,String>();
        nominees.put(1,"The Big Short");
        nominees.put(2,"Bridge of Spies");
        nominees.put(3,"Brooklyn");
        nominees.put(4,"Mad Max");
        nominees.put(5,"The Martian");
        nominees.put(6,"The Revenant");
        nominees.put(7,"Room");
        nominees.put(8,"Spotlight");

        Random r= new Random();
        int winner = r.nextInt(8);
        System.out.println("winner is: " + nominees.get(winner));
        assertEquals(winner, 3);

    }
}
