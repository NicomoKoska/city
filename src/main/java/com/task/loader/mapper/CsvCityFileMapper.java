package com.task.loader.mapper;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.task.city.entity.CityEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class CsvCityFileMapper implements CityFileMapper<CityEntity> {

  private static final char SEPARATOR = ',';

  @Override
  public List<CityEntity> mapFromFile(String filePath) throws IOException, URISyntaxException {
    File csvFile = loadFile(filePath);
    CsvMapper csvMapper = new CsvMapper();

    CsvSchema csvSchema = csvMapper
        .typedSchemaFor(CityEntity.class)
        .withHeader()
        .withColumnSeparator(SEPARATOR)
        .withComments();

    MappingIterator<CityEntity> cityListMappers = csvMapper
        .readerWithTypedSchemaFor(CityEntity.class)
        .with(csvSchema)
        .readValues(csvFile);

    return cityListMappers.readAll();
  }

  private File loadFile(String filePath) throws URISyntaxException, FileNotFoundException {
    URL resource = getClass().getClassLoader().getResource(filePath);
    if (Objects.isNull(resource)) {
      throw new FileNotFoundException("Wrong path for CSV file");
    }
    return new File(resource.toURI());
  }
}
