package com.invent.first.Service;

import com.invent.first.DTO.CityDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<CityDTO> getAllCity();
    CityDTO getCityById(Integer id);
}
