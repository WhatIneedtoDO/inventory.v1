package com.invent.first.Controller;

import com.invent.first.DTO.MonitorDTO;
import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.Monitor;
import com.invent.first.Service.HistoryService;
import com.invent.first.Service.MonitorService;
import com.invent.first.Service.TrashService;
import com.invent.first.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    private final TrashService trashService;


    public MonitorController(MonitorService monitorService, UserService userService, HistoryService historyService, TrashService trashService) {
        this.monitorService = monitorService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
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
                                                       @RequestBody MonitorDTO monitorDTO, @RequestParam(value = "trashdate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date trashdate) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);


        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer equipmentId = monitorId;
            Integer itemType = monitorDTO.getItemType();

            MonitorDTO originalMonitorDTO = monitorService.getMonitorById(monitorId);

            historyService.HistoryObject(originalMonitorDTO, monitorDTO, equipmentId, itemType, userid);

            if (monitorDTO.getSpisano() != null && monitorDTO.getSpisano().equals(true)) {
                trashService.TrashObject(monitorDTO, equipmentId, itemType, trashdate);
            }
            try {
                if (monitorDTO.getSpisano() == null || monitorDTO.getSpisano().equals(false)) {
                    trashService.deleteTrashObject(equipmentId, itemType);
                }
            } catch (EntityNotFoundException e) {
                // Обработка исключения, если запись Trash не найдена
            } catch (Exception ex) {
                // Обработка других исключений
            }
            Monitor updatedMonitor = monitorService.updateMonitor(monitorId, monitorDTO);
        }
        return ResponseEntity.ok(monitorService.getMonitorOutById(monitorId));
    }

    @DeleteMapping("/Delete/{monitorId}")
    public ResponseEntity<Void> deleteMonitor(@PathVariable Integer monitorId) {
        Monitor deletedMonitor = monitorService.deleteById(monitorId);
        trashService.deleteTrashObject(monitorId,deletedMonitor.getItemType().getId());
        if (deletedMonitor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
