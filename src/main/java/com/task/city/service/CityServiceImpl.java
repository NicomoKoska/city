package com.task.city.service;

import com.task.city.dto.UpdatePhotoDto;
import com.task.city.entity.CityEntity;
import com.task.city.exception.CityNotFoundException;
import com.task.city.repository.CityJpaRepository;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
public class CityServiceImpl implements CityService {

  private final CityJpaRepository cityJpaRepository;

  @Override
  public List<CityEntity> saveCities(List<CityEntity> cityEntities) {
    return cityJpaRepository.saveAll(cityEntities);
  }

  @Override
  public List<CityEntity> findAllByName(String nameLike, Pageable pageable) {
    return cityJpaRepository.findAllByNameLike(nameLike, pageable);
  }

  @Override
  public List<CityEntity> findAll() {
    return cityJpaRepository.findAll();
  }

  @Override
  public CityEntity updatePhoto(UpdatePhotoDto updatePhotoDto) {
    val id = updatePhotoDto.getId();
    CityEntity cityEntity = cityJpaRepository.findById(id)
        .orElseThrow(() -> new CityNotFoundException(id));

    cityEntity.setPhoto(updatePhotoDto.getPhoto());
    return cityJpaRepository.save(cityEntity);
  }
}
