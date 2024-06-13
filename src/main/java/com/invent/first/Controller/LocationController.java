package com.invent.first.Controller;

import com.invent.first.DTO.LocationDTO;
import com.invent.first.Service.LocationService;
import com.invent.first.response.EkpJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Location")
public class LocationController {
    private final LocationService locationService;
    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocationDTO>> getAllLocations(){
        List<LocationDTO> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/{ekp}")
    public ResponseEntity<List<EkpJsonResponse>> getByEkp(@PathVariable Integer ekp){
        List<EkpJsonResponse> byEkpList = locationService.getByEkp(ekp);
        return ResponseEntity.ok(byEkpList);
    }
}
