package com.task.city.service;

import com.task.city.dto.UpdatePhotoDto;
import com.task.city.entity.CityEntity;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

  List<CityEntity> saveCities(List<CityEntity> cityEntities);

  List<CityEntity> findAllByName(String nameLike, Pageable pageable);

  List<CityEntity> findAll();

  CityEntity updatePhoto(UpdatePhotoDto updatePhotoDto);

}
