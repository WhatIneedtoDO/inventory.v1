package com.alibou.security.Service;

import com.alibou.security.DTO.CityDTO;
import com.alibou.security.Entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<CityDTO> getAllCity();
}
