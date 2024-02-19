package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.DTO.OutDTO.ServerEqsOutDTO;
import com.invent.first.DTO.ServerEqsDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.ServerEqs;
import com.invent.first.Service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ServerEquipments")
public class ServerEqsController {
    private final ServerEqsService serverEqsService;
    private final UserService userService;
    private final HistoryService historyService;
    private final TrashService trashService;
    private final ExcelExportService<ServerEqsOutDTO> excelExportService;

    @Autowired
    public ServerEqsController(ServerEqsService serverEqsService, UserService userService,
                               HistoryService historyService, TrashService trashService,ExcelExportService<ServerEqsOutDTO> excelExportService) {
        this.serverEqsService = serverEqsService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
        this.excelExportService = excelExportService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServerEqsOutDTO>> getAllServerEqs() {
        List<ServerEqsOutDTO> serverEqs = serverEqsService.getAllServerEqsWithDetails();
        return ResponseEntity.ok(serverEqs);
    }

    @GetMapping("/Details/{eqsId}")
    public ResponseEntity<ServerEqsOutDTO> getServerEqsDetails(@PathVariable Integer eqsId) {
        ServerEqsOutDTO serverEqsById = serverEqsService.getServerEqsOutById(eqsId);
        return ResponseEntity.ok(serverEqsById);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read')")
    @GetMapping("/Departments")
    public ResponseEntity<List<ServerEqsOutDTO>> getByDepartments(@AuthenticationPrincipal UserDetails userDetails) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        Integer deptId = null;
        if (user.isPresent() && user.get().getDepartment() != null) {
            deptId = user.get().getDepartment().getId();
        } else {
            // В случае, если id отдела равен null, вернуть ResponseEntity с соответствующим кодом состояния
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<ServerEqsOutDTO> serverEqsByDeptId = serverEqsService.getByDept(deptId);
        return ResponseEntity.ok(serverEqsByDeptId);
    }

    @PostMapping("/add")
    public ResponseEntity<ServerEqsDTO> addServerEqs(@RequestBody ServerEqsDTO serverEqsDTO) {
        ServerEqsDTO addedServerEqs = serverEqsService.addServerEqs(serverEqsDTO);
        return new ResponseEntity<>(addedServerEqs, HttpStatus.CREATED);
    }

    @PutMapping("/Update/{eqsId}")
    public ResponseEntity<ServerEqsOutDTO> updateServerEqs(@PathVariable Integer eqsId, @AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody ServerEqsDTO serverEqsDTO, @RequestParam(value = "trashdate", required = false)
                                                           @DateTimeFormat(pattern = "dd-MM-yyyy") Date trashdate) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);


        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer itemType = serverEqsDTO.getItemType();

            ServerEqsDTO originalServerEqsDTO = serverEqsService.getServerEqsById(eqsId);
            historyService.HistoryObject(originalServerEqsDTO, serverEqsDTO, eqsId, itemType, userid);

            if (serverEqsDTO.getSpisano()!=null && serverEqsDTO.getSpisano().equals(true)){
                trashService.TrashObject(serverEqsDTO,eqsId,itemType,trashdate);
            }
            try {
                if (serverEqsDTO.getSpisano() == null || serverEqsDTO.getSpisano().equals(false)){
                    trashService.deleteTrashObject(eqsId, itemType);
                }

            } catch (EntityNotFoundException e) {
                // Обработка исключения, если запись Trash не найдена
            } catch (Exception ex) {
                // Обработка других исключений
            }

            ServerEqs updatedServerEqs = serverEqsService.updateServerEqs(eqsId,serverEqsDTO);
        }
        return ResponseEntity.ok(serverEqsService.getServerEqsOutById(eqsId));
    }
    @DeleteMapping("/Delete/{eqsId}")
    public ResponseEntity<Void> deleteServerEqs(@PathVariable Integer eqsId){
        ServerEqs deletedServerEqs = serverEqsService.deleteById(eqsId);
        trashService.deleteTrashObject(eqsId,deletedServerEqs.getItemType().getId());
        if (deletedServerEqs == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    @SneakyThrows
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) {
        List<ServerEqsOutDTO> serverEqs = serverEqsService.getAllServerEqsWithDetails();
        excelExportService.exportToExcel(serverEqs, ServerEqsOutDTO.class, response);
    }
}
