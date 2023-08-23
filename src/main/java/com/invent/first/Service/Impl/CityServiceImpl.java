package com.invent.first.Service.Impl;

import com.invent.first.DTO.CityDTO;
import com.invent.first.Entity.City;
import com.invent.first.Repository.CityRepository;
import com.invent.first.Service.CityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    @Autowired
    public CityServiceImpl(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }
    @Override
    public List<CityDTO> getAllCity() {
        List<City> cityDTOS = cityRepository.findAll();
        return mapToDTOs(cityDTOS);
    }

    @Override
    public CityDTO getCityById(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("City Not Found"));
        return mapToDTO(city);
    }

    private List<CityDTO> mapToDTOs(List<City> cities){
        return cities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private CityDTO mapToDTO(City city){
        return CityDTO
                .builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}
