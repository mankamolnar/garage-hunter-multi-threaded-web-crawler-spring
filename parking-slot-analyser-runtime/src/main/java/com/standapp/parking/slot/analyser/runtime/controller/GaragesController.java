package com.standapp.parking.slot.analyser.runtime.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.standapp.parking.slot.analyser.persistence.GarageRepository;
import com.standapp.parking.slot.analyser.persistence.domain.DbGarage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@RequestMapping("/garages")
@RequiredArgsConstructor
@Slf4j
@Deprecated
public class GaragesController {

  private final GarageRepository garageRepository;

  @RequestMapping(method = RequestMethod.GET)
  public String greeting() throws JsonProcessingException {
    List<DbGarage> garages = garageRepository.findAllByEndDateIsNotNull();

    ObjectMapper mapper = new ObjectMapper();

    return mapper.writeValueAsString(garages);
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }
}
