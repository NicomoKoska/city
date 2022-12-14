package com.task.boot;

import com.task.city.service.CityService;
import com.task.loader.CityLoader;
import com.task.loader.CsvCityLoader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

  @Value("${city-app.init.file}")
  private String filePath;

  @Bean
  @ConditionalOnProperty(
      value="spring.sql.init.mode",
      havingValue = "always"
  )
  public CityLoader csvCityLoader(CityService cityService) {
    return new CsvCityLoader(cityService, filePath);
  }

}
