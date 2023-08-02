package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.CityDTO;
import com.alibou.security.Entity.City;
import com.alibou.security.Repository.CityRepository;
import com.alibou.security.Service.CityService;
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
