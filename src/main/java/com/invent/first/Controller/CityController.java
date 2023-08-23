package com.invent.first.Controller;

import com.invent.first.DTO.CityDTO;
import com.invent.first.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/City")
public class CityController {
    private final CityService cityService;
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAllCity(){
        List<CityDTO> cities = cityService.getAllCity();
        return ResponseEntity.ok(cities);
    }
}
