package com.task.loader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.task.city.entity.CityEntity;
import com.task.city.service.CityService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class CsvCityLoader implements CityLoader {

  private CityService cityService;

  private String filePath;

  @Override
  @EventListener(ApplicationReadyEvent.class)
  public void loadCities() throws IOException {
    if (!cityService.findAll().isEmpty()) {
      return;
    }

    File csvFile = new File(filePath);
    CsvMapper csvMapper = new CsvMapper();

    CsvSchema csvSchema = csvMapper
        .typedSchemaFor(CityEntity.class)
        .withHeader()
        .withColumnSeparator(',')
        .withComments();

    MappingIterator<CityEntity> cityListMappers = csvMapper
        .readerWithTypedSchemaFor(CityEntity.class)
        .with(csvSchema)
        .readValues(csvFile);

    List<CityEntity> cityEntities = cityListMappers.readAll();
    cityService.saveCities(cityEntities);
  }
}
