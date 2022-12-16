package com.task.loader;

import com.task.city.entity.CityEntity;
import com.task.city.service.CityService;
import com.task.loader.mapper.CityFileMapper;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@AllArgsConstructor
public class CityLoaderImpl implements CityLoader {

  private CityFileMapper<CityEntity> cityFileMapper;
  private CityService cityService;

  private String filePath;

  @Override
  @EventListener(ApplicationReadyEvent.class)
  public void loadCities() throws IOException, URISyntaxException {
    if (hasRecords()) {
      return;
    }

    List<CityEntity> cityEntities = cityFileMapper.mapFromFile(filePath);
    cityService.saveCities(cityEntities);
  }

  private boolean hasRecords() {
    return !cityService.findPaginated(PageRequest.of(0, 1))
        .isEmpty();
  }
}
