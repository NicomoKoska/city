package com.task.boot;

import com.task.city.repository.CityJpaRepository;
import com.task.city.rest.CityController;
import com.task.city.rest.CityControllerImpl;
import com.task.city.service.CityService;
import com.task.city.service.CityServiceImpl;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.task.city")
@EntityScan("com.task.city")
public class ServiceConfig {

  @Bean
  public CityService cityService(CityJpaRepository cityJpaRepository) {
    return new CityServiceImpl(cityJpaRepository);
  }

  @Bean
  public CityController cityController(CityService cityService) {
    return new CityControllerImpl(cityService);
  }
}
