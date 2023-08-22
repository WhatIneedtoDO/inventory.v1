package com.alibou.security.Service;

import com.alibou.security.DTO.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LocationService {
    List<LocationDTO> getAllLocations();
    LocationDTO getLocationById(Integer id);
}
