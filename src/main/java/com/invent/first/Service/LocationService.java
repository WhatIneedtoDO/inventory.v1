package com.invent.first.Service;

import com.invent.first.DTO.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LocationService {
    List<LocationDTO> getAllLocations();
    LocationDTO getLocationById(Integer id);
}
