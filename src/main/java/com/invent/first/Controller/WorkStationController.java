package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.WorkstationOutDTO;
import com.invent.first.Service.Impl.WorkStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Workstations")
public class WorkStationController {

    private final WorkStationService workStationService;

    @Autowired
    public WorkStationController(WorkStationService workStationService) {
        this.workStationService = workStationService;
    }
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAllWorkStations() {
        List<Object> workstations = workStationService.getAllWorkstations();
        return ResponseEntity.ok(workstations);
    }
}
