package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.WorkstationOutDTO;
import com.invent.first.Repository.WorkStationRepositoryJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Workstations")
public class WorkStationController {
    private final WorkStationRepositoryJDBC workStationRepository;

    @Autowired
    public WorkStationController(WorkStationRepositoryJDBC workStationRepository){
        this.workStationRepository = workStationRepository;
    }
    @GetMapping("/all")
    public ResponseEntity<List<WorkstationOutDTO>> getAllWorkStations() {
        List<WorkstationOutDTO> workstations = workStationRepository.findAllWorkstation();
        return ResponseEntity.ok(workstations);
    }
}
