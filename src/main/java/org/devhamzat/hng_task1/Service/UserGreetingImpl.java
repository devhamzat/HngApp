package org.devhamzat.hng_task1.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.devhamzat.hng_task1.Dto.UserResponseDto;
import org.devhamzat.hng_task1.Dto.WeatherApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
@Service
public class UserGreetingImpl implements UserGreetingService {
    private static final String API_KEY = "e644bcefff4c41fea1b143024240107";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserResponseDto userResponse(String visitor_name, HttpServletRequest httpServletRequest) {
        String userIpAddress = httpServletRequest.getHeader("X-Forwarded-For");
        if (userIpAddress == null) {
            userIpAddress = httpServletRequest.getRemoteAddr();
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("key", API_KEY)
                .queryParam("q", userIpAddress);
        ResponseEntity<WeatherApiData> response = restTemplate.getForEntity(uriBuilder.toUriString(), WeatherApiData.class);
        WeatherApiData responseBody = response.getBody();

        if (responseBody != null) {

            WeatherApiData.Location location = responseBody.getLocation();
            WeatherApiData.Current tempInfo = responseBody.getCurrent();

            String city = location.getRegion();
            double temperature = tempInfo.getTemp_c();
            UserResponseDto userResponseDto = new UserResponseDto();

            userResponseDto.setLocation(city);
            userResponseDto.setClient_Ip(userIpAddress);
            String message = "Hello, " + visitor_name + "!, the temperature is "
                    + temperature + " degrees Celsius in " + userResponseDto.getLocation();
            userResponseDto.setGreeting(message);
            return userResponseDto;

        }
        return new UserResponseDto();
    }
}
