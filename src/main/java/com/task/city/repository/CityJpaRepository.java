package com.task.city.repository;

import com.task.city.entity.CityEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CityJpaRepository extends JpaRepository<CityEntity, Long>,
    PagingAndSortingRepository<CityEntity, Long> {

  List<CityEntity> findAllByNameLike(String name, Pageable pageable);

}
