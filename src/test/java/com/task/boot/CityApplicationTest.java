package com.task.boot;

import com.task.city.entity.CityEntity;
import com.task.city.repository.CityJpaRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import({InitConfig.class, ServiceConfig.class})
class CityApplicationTest {

  @Autowired
  private CityJpaRepository cityJpaRepository;

  @Test
  void testInitCityList() {
    List<CityEntity> cityEntities = cityJpaRepository.findAll();

    Assertions.assertEquals(2, cityEntities.size());
  }

}
