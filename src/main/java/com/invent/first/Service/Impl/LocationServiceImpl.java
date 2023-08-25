package com.invent.first.Service.Impl;

import com.invent.first.DTO.LocationDTO;
import com.invent.first.Entity.Location;
import com.invent.first.Repository.LocationRepository;
import com.invent.first.Service.LocationService;
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
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }
    @Override
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return mapToDTOs(locations);
    }

    @Override
    public LocationDTO getLocationById(Integer id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Location not found"));
        return mapToDTO(location);
    }

    private List<LocationDTO> mapToDTOs(List<Location> locations){
        return locations.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private LocationDTO mapToDTO(Location location){
        return LocationDTO
                .builder()
                .id(location.getId())
                .ekp(location.getEkp())
                .street(location.getStreet())
                .number(location.getNumber())
                .build();
    }
}