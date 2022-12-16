package com.task.loader;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.city.entity.CityEntity;
import com.task.city.service.CityService;
import com.task.loader.mapper.CityFileMapper;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CityLoaderImplTest {

  private static final String PATH = "cities.csv";

  private CityLoaderImpl target;

  @Mock
  private CityFileMapper<CityEntity> cityFileMapper;
  @Mock
  private CityService cityService;

  @BeforeEach
  void init() {
    target = new CityLoaderImpl(cityFileMapper, cityService, PATH);
  }

  @Test
  void testLoadCities() throws IOException, URISyntaxException {
    when(cityService.findPaginated(PageRequest.of(0, 1))).thenReturn(Page.empty());

    val mockCityEntity = Mockito.mock(CityEntity.class);
    List<CityEntity> mockList = List.of(mockCityEntity);
    when(cityFileMapper.mapFromFile(PATH)).thenReturn(mockList);
    when(cityService.saveCities(mockList)).thenReturn(mockList);

    target.loadCities();

    verify(cityFileMapper, times(1)).mapFromFile(PATH);
    verify(cityService, times(1)).saveCities(mockList);
  }

  @Test
  void testLoadCities_hasRecords() throws IOException, URISyntaxException {
    val mockCityEntity = Mockito.mock(CityEntity.class);
    Page<CityEntity> page = new PageImpl<>(List.of(mockCityEntity));
    when(cityService.findPaginated(PageRequest.of(0, 1))).thenReturn(page);

    target.loadCities();

    verify(cityFileMapper, times(0)).mapFromFile(PATH);
    verify(cityService, times(0)).saveCities(anyList());
  }

}
