package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.DTO.OutDTO.PowerSystemOutDTO;
import com.invent.first.DTO.OutDTO.ServerEqsOutDTO;
import com.invent.first.DTO.PowerSystemDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.PowerSystem;
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
@RequestMapping("/api/v1/PowerSystems")
public class PowerSysController {
    private final PowerSystemService powerSystemService;
    private final UserService userService;
    private final HistoryService historyService;
    private final TrashService trashService;
    private final ExcelExportService<PowerSystemOutDTO> excelExportService;
    @Autowired
    public PowerSysController(PowerSystemService powerSystemService, UserService userService,
                              HistoryService historyService, TrashService trashService, ExcelExportService<PowerSystemOutDTO> excelExportService) {
        this.powerSystemService = powerSystemService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
        this.excelExportService = excelExportService;
    }
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/all")
    public ResponseEntity<List<PowerSystemOutDTO>> getAllPowerSys(){
        List<PowerSystemOutDTO> powerSys = powerSystemService.getAllPSWithDetails();
        return ResponseEntity.ok(powerSys);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Details/{psId}")
    public ResponseEntity<PowerSystemOutDTO> getPowerSysDetails(@PathVariable Integer psId){
        PowerSystemOutDTO powerSystemById = powerSystemService.getPSOutById(psId);
        return ResponseEntity.ok(powerSystemById);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Departments")
    public ResponseEntity<List<PowerSystemOutDTO>> getByDepartments(@AuthenticationPrincipal UserDetails userDetails) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        Integer deptId = null;
        if (user.isPresent() && user.get().getDepartment() != null) {
            deptId = user.get().getDepartment().getId();
        } else {
            // В случае, если id отдела равен null, вернуть ResponseEntity с соответствующим кодом состояния
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<PowerSystemOutDTO> psByDeptId = powerSystemService.getByDept(deptId);
        return ResponseEntity.ok(psByDeptId);
    }
    @PreAuthorize("hasAnyAuthority('admin:create','management:create')")
    @PostMapping("/add")
    public ResponseEntity<PowerSystemDTO> addPowerSys(@RequestBody PowerSystemDTO powerSystemDTO){
        PowerSystemDTO addedPowerSys = powerSystemService.addPowerSystem(powerSystemDTO);
        return new ResponseEntity<>(addedPowerSys, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('admin:update','management:update')")
    @PutMapping("/Update/{psId}")
    public ResponseEntity<PowerSystemOutDTO> updatePowerSys(@PathVariable Integer psId, @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody PowerSystemDTO powerSystemDTO, @RequestParam(value = "trashdate", required = false)
                                                                @DateTimeFormat(pattern = "dd-MM-yyyy") Date trashdate){
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);


        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer itemType = powerSystemDTO.getItemType();

            PowerSystemDTO originalPowerSysDTO = powerSystemService.getPSById(psId);
            historyService.HistoryObject(originalPowerSysDTO, powerSystemDTO, psId, itemType, userid);

            if (powerSystemDTO.getSpisano()!=null && powerSystemDTO.getSpisano().equals(true)){
                trashService.TrashObject(powerSystemDTO,psId,itemType,trashdate);
            }
            try {
                if (powerSystemDTO.getSpisano() == null || powerSystemDTO.getSpisano().equals(false)){
                    trashService.deleteTrashObject(psId, itemType);
                }

            } catch (EntityNotFoundException e) {
                // Обработка исключения, если запись Trash не найдена
            } catch (Exception ex) {
                // Обработка других исключений
            }

            PowerSystem updatedPS = powerSystemService.updatePS(psId,powerSystemDTO);
        }
        return ResponseEntity.ok(powerSystemService.getPSOutById(psId));
    }
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/Delete/{psId}")
    public ResponseEntity<Void> deletePowerSys(@PathVariable Integer psId){
        PowerSystem deletedPowerSys = powerSystemService.deleteById(psId);
        trashService.deleteTrashObject(psId,deletedPowerSys.getItemType().getId());
        if (deletedPowerSys == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    @SneakyThrows
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) {
        List<PowerSystemOutDTO> powerSystems = powerSystemService.getAllPSWithDetails();
      excelExportService.exportToExcel(powerSystems,PowerSystemOutDTO.class,response);
    }
}
