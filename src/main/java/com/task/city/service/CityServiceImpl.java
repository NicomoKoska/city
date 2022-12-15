package com.task.city.service;

import com.task.city.dto.UpdateCityDto;
import com.task.city.entity.CityEntity;
import com.task.city.exception.CityNotFoundException;
import com.task.city.repository.CityJpaRepository;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
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
  public Page<CityEntity> findPaginatedByName(String name, Pageable pageable) {
    return cityJpaRepository.findAllByNameContainingIgnoreCase(name, pageable);
  }

  @Override
  public Page<CityEntity> findPaginated(Pageable pageable) {
    return cityJpaRepository.findAll(pageable);
  }

  @Override
  public CityEntity findById(Long id) {
    return cityJpaRepository.findById(id)
        .orElseThrow(() -> new CityNotFoundException(id));
  }

  @Override
  public CityEntity updateCity(UpdateCityDto updateCityDto) {
    val id = updateCityDto.getId();
    CityEntity cityEntity = cityJpaRepository.findById(id)
        .orElseThrow(() -> new CityNotFoundException(id));

    cityEntity.setName(updateCityDto.getName());
    cityEntity.setPhoto(updateCityDto.getPhoto());
    return cityJpaRepository.save(cityEntity);
  }
}
