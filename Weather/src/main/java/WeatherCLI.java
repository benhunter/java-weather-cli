import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonReader;


public class WeatherCLI {
    private final String API_KEY;
    private final static String ENVIRONMENT_VARIABLE_API_KEY = "OPEN_WEATHER_API_KEY";

    public static void main(String[] args) {

    }

    public WeatherCLI() {
        API_KEY = System.getenv(ENVIRONMENT_VARIABLE_API_KEY);
    }

    public String currentTemperature(int zipcode) {
        /*
        api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={API key}

        Environment variable with API key for openweathermap.org:
            echo $OPEN_WEATHER_API_KEY
            Key stored in ~/.bash_profile
         */

        String returnMessage = null;
        String degreesFahrenheit;

        HttpClient httpClient = HttpClient.newBuilder().build();

        String uriString = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + "&units=imperial&appid=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uriString)).build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            InputStream targetStream = new ByteArrayInputStream(response.body().getBytes());
            JsonReader jsonReader = Json.createReader(targetStream);

            degreesFahrenheit = jsonReader.readObject().getJsonObject("main").getJsonNumber("temp").toString();
            returnMessage = "Currently " + degreesFahrenheit + " degrees Fahrenheit in zip code " + zipcode;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (returnMessage == null) returnMessage = "Could not check the temperature.";
        return returnMessage;
    }
}
