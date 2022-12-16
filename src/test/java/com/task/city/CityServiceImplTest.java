package com.task.city;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.task.city.dto.UpdateCityDto;
import com.task.city.entity.CityEntity;
import com.task.city.repository.CityJpaRepository;
import com.task.city.service.CityServiceImpl;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

  private static final Long ID = 1L;
  private static final String NAME = "TEST-NAME";
  private static final String PHOTO = "LINK";

  @InjectMocks
  private CityServiceImpl target;

  @Mock
  private CityJpaRepository cityJpaRepository;

  @Test
  void testUpdateCityEntity() {
    val updateCityDto = UpdateCityDto.builder()
        .id(ID)
        .name(NAME)
        .photo(PHOTO)
        .build();

    val cityEntity = new CityEntity(ID, "JJJ", "KKK");
    when(cityJpaRepository.findById(ID)).thenReturn(Optional.of(cityEntity));

    cityEntity.setName(NAME);
    cityEntity.setPhoto(PHOTO);
    when(cityJpaRepository.save(cityEntity)).thenReturn(cityEntity);

    val result = target.updateCity(updateCityDto);
    assertEquals(ID, result.getId());
    assertEquals(NAME, result.getName());
    assertEquals(PHOTO, result.getPhoto());
  }


}
