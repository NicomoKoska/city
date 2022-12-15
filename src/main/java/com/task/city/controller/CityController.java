package com.task.city.controller;

import com.task.city.dto.UpdateCityDto;
import com.task.city.entity.CityEntity;
import com.task.city.service.CityService;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
@AllArgsConstructor
public class CityController {

  private final CityService cityService;

  @GetMapping("/cities")
  public String getAll(Model model,
      @RequestParam(required = false) String name,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    try {
      Pageable pageable = PageRequest.of(page - 1, size);

      Page<CityEntity> pageCities;
      if (StringUtils.isEmptyOrWhitespace(name) ) {
        pageCities = cityService.findPaginated(pageable);
      } else {
        pageCities = cityService.findPaginatedByName(name, pageable);
        model.addAttribute("name", name);
      }

      List<CityEntity> cityEntities = pageCities.getContent();

      model.addAttribute("cities", cityEntities);
      model.addAttribute("currentPage", pageCities.getNumber() + 1);
      model.addAttribute("totalItems", pageCities.getTotalElements());
      model.addAttribute("totalPages", pageCities.getTotalPages());
      model.addAttribute("pageSize", size);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "cities";
  }


  @GetMapping("/cities/{id}")
  public String editCity(@PathVariable("id") Long id,
      Model model, RedirectAttributes redirectAttributes) {
    try {
      CityEntity cityEntity = cityService.findById(id);

      model.addAttribute("city", cityEntity);
      model.addAttribute("pageTitle", "Edit City (ID: " + id + ")");

      return "city_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());

      return "redirect:/cities";
    }
  }

  @PostMapping("/city/save")
  public String saveTutorial(UpdateCityDto city,
      RedirectAttributes redirectAttributes) {
    try {
      cityService.updateCity(city);

      redirectAttributes.addFlashAttribute("message",
          "City has been saved successfully!");
    } catch (Exception e) {
      redirectAttributes.addAttribute("message", e.getMessage());
    }

    return "redirect:/cities";
  }
}
