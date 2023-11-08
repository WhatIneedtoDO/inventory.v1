package com.invent.first.Service;

import com.invent.first.DTO.LocationDTO;
import com.invent.first.response.EkpJsonResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LocationService {
    List<LocationDTO> getAllLocations();
    LocationDTO getLocationById(Integer id);
    List<EkpJsonResponse> getByEkp(Integer ekp);
}
