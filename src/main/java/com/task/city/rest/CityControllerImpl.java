package com.task.city.rest;

import com.task.city.dto.UpdatePhotoDto;
import com.task.city.entity.CityEntity;
import com.task.city.service.CityService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;

import java.util.List;

@AllArgsConstructor
public class CityControllerImpl implements CityController {

  private final CityService cityService;

  @Override
  public List<CityEntity> findCitiesByName(String name,
      Integer page, Integer pageSize) {
    String nameLike = "%" + name + "%";
    return cityService.findAllByName(nameLike, PageRequest.of(page, pageSize));
  }

  @Override
  public CityEntity updateCityPhoto(UpdatePhotoDto updatePhotoDto) {
    return cityService.updatePhoto(updatePhotoDto);
  }

}
