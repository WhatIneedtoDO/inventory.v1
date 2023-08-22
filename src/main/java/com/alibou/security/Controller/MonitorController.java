package com.alibou.security.Controller;

import com.alibou.security.DTO.HistoryDTO;
import com.alibou.security.DTO.MonitorDTO;
import com.alibou.security.DTO.OutDTO.MonitorOutDTO;
import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.Computer;
import com.alibou.security.Entity.Monitor;
import com.alibou.security.Service.HistoryService;
import com.alibou.security.Service.MonitorService;
import com.alibou.security.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Monitors")
public class MonitorController {
    private final MonitorService monitorService;
    private final UserService userService;
    private final HistoryService historyService;


    public MonitorController(MonitorService monitorService, UserService userService, HistoryService historyService) {
        this.monitorService = monitorService;
        this.userService = userService;
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MonitorOutDTO>> getAllMonitors() {
        List<MonitorOutDTO> monitors = monitorService.getAllMonitorsWithDetails();
        return ResponseEntity.ok(monitors);
    }

    @GetMapping("/Details/{monitorId}")
    public ResponseEntity<MonitorOutDTO> getMonitorDetails(@PathVariable Integer monitorId) {
        MonitorOutDTO monitorById = monitorService.getMonitorOutById(monitorId);
        return ResponseEntity.ok(monitorById);
    }

    @PostMapping("/add")
    public ResponseEntity<MonitorDTO> addMonitor(@RequestBody MonitorDTO monitorDTO) {
        MonitorDTO addedMonitor = monitorService.addMonitor(monitorDTO);
        return new ResponseEntity<>(addedMonitor, HttpStatus.CREATED);
    }

    @PutMapping("/Update/{monitorId}")
    public ResponseEntity<MonitorOutDTO> updateMonitor(@PathVariable Integer monitorId, @AuthenticationPrincipal UserDetails userDetails,
                                                       @RequestBody MonitorDTO monitorDTO) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);


        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer equipmentId = monitorId;
            Integer itemType = monitorDTO.getItemType();

            MonitorDTO originalMonitorDTO = monitorService.getMonitorById(monitorId);

            historyService.HistoryObject(originalMonitorDTO,monitorDTO,equipmentId,itemType,userid);

            Monitor updatedMonitor = monitorService.updateMonitor(monitorId,monitorDTO);
        }
        return ResponseEntity.ok(monitorService.getMonitorOutById(monitorId));
    }

    @DeleteMapping("/Delete/{monitorId}")
    public ResponseEntity<Void> deleteMonitor(@PathVariable Integer monitorId) {
        Monitor deletedMonitor = monitorService.deleteById(monitorId);
        if (deletedMonitor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
