package com.task.city.rest;

import com.task.city.dto.UpdatePhotoDto;
import com.task.city.entity.CityEntity;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CityController {

  @GetMapping(path = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  List<CityEntity> findCitiesByName(
      @RequestParam String name,
      @RequestParam Integer page,
      @RequestParam Integer pageSize);

  @PostMapping(path = "/city/photo", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  CityEntity updateCityPhoto(
      @RequestBody UpdatePhotoDto updatePhotoDto
  );

}
