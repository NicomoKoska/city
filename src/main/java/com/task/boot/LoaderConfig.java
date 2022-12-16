package com.task.boot;

import com.task.city.entity.CityEntity;
import com.task.city.service.CityService;
import com.task.loader.CityLoader;
import com.task.loader.CityLoaderImpl;
import com.task.loader.mapper.CityFileMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoaderConfig {

  @Value("${city-app.init.file}")
  private String filePath;

  @Bean
  @ConditionalOnProperty(
      value="spring.sql.init.mode",
      havingValue = "always"
  )
  public CityLoader csvCityLoader(CityFileMapper<CityEntity> csvCityFileMapper, CityService cityService) {
    return new CityLoaderImpl(csvCityFileMapper, cityService, filePath);
  }

}
