package com.task.loader.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.task.city.entity.CityEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

class CsvCityMapperTest {

  private static final String PATH = "cities.csv";
  private static final Long ID = 1L;
  private static final String NAME = "TestCity";
  private static final String PHOTO = "TestLink";

  private CsvCityFileMapper mapper;

  @BeforeEach
  void init() {
    mapper = new CsvCityFileMapper();
  }

  @Test
  void testMappingFromPath() throws IOException, URISyntaxException {
    List<CityEntity> result = mapper.mapFromFile(PATH);

    assertNotNull(result);
    assertEquals(2, result.size());

    CityEntity record = result.get(0);
    assertEquals(ID, record.getId());
    assertEquals(NAME, record.getName());
    assertEquals(PHOTO, record.getPhoto());

  }

}
