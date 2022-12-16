package com.task.loader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.task.city.entity.CityEntity;
import com.task.city.service.CityService;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CsvCityLoader implements CityLoader {

  private CityService cityService;

  private String filePath;

  @Override
  @EventListener(ApplicationReadyEvent.class)
  public void loadCities() throws IOException, URISyntaxException {
    if (hasRecords()) {
      return;
    }

    File csvFile = loadFile();
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

  private File loadFile() throws URISyntaxException, FileNotFoundException {
    URL resource = getClass().getClassLoader().getResource(filePath);
    if (Objects.isNull(resource)) {
      throw new FileNotFoundException("Wrong path for CSV file");
    }
    return new File(resource.toURI());
  }

  private boolean hasRecords() {
    return !cityService.findPaginated(PageRequest.of(0, 1))
        .isEmpty();
  }
}
