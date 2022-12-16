package com.task.boot;

import com.task.city.entity.CityEntity;
import com.task.loader.mapper.CityFileMapper;
import com.task.loader.mapper.CsvCityFileMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileMapperConfig {

  @Bean
  public CityFileMapper<CityEntity> csvCityFileMapper() {
    return new CsvCityFileMapper();
  }

}
