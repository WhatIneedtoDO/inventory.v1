package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.ComputerOutDTO;
import com.invent.first.DTO.OutDTO.WorkstationOutDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Service.Impl.WorkStationService;
import com.invent.first.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Workstations")
public class WorkStationController {

    private final WorkStationService workStationService;
    private final UserService userService;
    @Autowired
    public WorkStationController(WorkStationService workStationService, UserService userService) {
        this.workStationService = workStationService;
        this.userService = userService;
    }
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAllWorkStations() {
        List<Object> workstations = workStationService.getAllWorkstations();
        return ResponseEntity.ok(workstations);
    }

    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Departments")
    public ResponseEntity<List<Object>> getByDepartments(@AuthenticationPrincipal UserDetails userDetails) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        Integer deptId = null;
        if (user.isPresent() && user.get().getDepartment() != null) {
            deptId = user.get().getDepartment().getId();
        } else {
            // В случае, если id отдела равен null, вернуть ResponseEntity с соответствующим кодом состояния
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Object> workstationsByDept = workStationService.getWorkstationsByDept(deptId);
        return ResponseEntity.ok(workstationsByDept);
    }
}
