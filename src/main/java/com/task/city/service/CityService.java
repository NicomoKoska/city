package com.task.city.service;

import com.task.city.dto.UpdateCityDto;
import com.task.city.entity.CityEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

  List<CityEntity> saveCities(List<CityEntity> cityEntities);

  Page<CityEntity> findPaginatedByName(String name, Pageable pageable);

  Page<CityEntity> findPaginated(Pageable pageable);

  CityEntity findById(Long id);

  CityEntity updateCity(UpdateCityDto updateCityDto);

}
