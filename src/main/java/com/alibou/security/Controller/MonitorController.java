package com.alibou.security.Controller;

import com.alibou.security.DTO.MonitorDTO;
import com.alibou.security.DTO.OutDTO.ComputerOutDTO;
import com.alibou.security.DTO.OutDTO.MonitorOutDTO;
import com.alibou.security.Service.MonitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Monitors")
public class MonitorController {
    private final MonitorService monitorService;


    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MonitorOutDTO>> getAllMonitors() {
        List<MonitorOutDTO> monitors = monitorService.getAllMonitorsWithDetails();
        return ResponseEntity.ok(monitors);
    }
    @PostMapping("/add")
    public ResponseEntity<MonitorDTO> addMonitor(@RequestBody MonitorDTO monitorDTO){
        MonitorDTO addedMonitor = monitorService.addMonitor(monitorDTO);
        return new ResponseEntity<>(addedMonitor, HttpStatus.CREATED);
    }

}
