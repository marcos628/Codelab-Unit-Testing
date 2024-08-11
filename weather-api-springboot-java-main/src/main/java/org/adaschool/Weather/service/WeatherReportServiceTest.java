package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherApiResponse.Main;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherReportServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    public void testGetWeatherReport() {
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        Main mockMain = new Main();
        mockMain.setTemperature(25.0);
        mockMain.setHumidity(70);
        mockApiResponse.setMain(mockMain);

        when(restTemplate.getForObject(anyString(), WeatherApiResponse.class)).thenReturn(mockApiResponse);

        WeatherReport report = weatherReportService.getWeatherReport(51.5074, -0.1278); // Coordenadas de Londres

        assertEquals(25.0, report.getTemperature());
        assertEquals(70, report.getHumidity());
    }
}
