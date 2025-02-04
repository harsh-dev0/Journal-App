package xyz.znet.journalapp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Main main;
    private List<Weather> weather;

    @Getter
    @Setter
    public static class Main {
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;
    }

    @Getter
    @Setter
    public static class Weather {
        private String description;
    }
}
