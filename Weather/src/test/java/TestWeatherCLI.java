import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWeatherCLI {

    @Test
    void testTemperatureAcceptsZipCodeAndReturnsTemperatureFahrenheit() {
        String expectedRegex = "Currently \\d*?.\\d* degrees Fahrenheit in zip code \\d+";
        int zipcode = 10001;
        WeatherCLI weatherCLI = new WeatherCLI();

        String actual = weatherCLI.currentTemperature(zipcode);
        assertTrue(actual.matches(expectedRegex));
    }

    @Test
    void testMain() {
        assert false;
    }
}
