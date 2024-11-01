package com.invent.first.Controller;

import com.invent.first.DTO.ComputerDTO;
import com.invent.first.DTO.OutDTO.ComputerOutDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.Computer;
import com.invent.first.Service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Computers")
public class ComputerController {
    private final ComputerService computerService;
    private final UserService userService;
    private final HistoryService historyService;
    private final TrashService trashService;
    private final ExcelExportService<ComputerOutDTO> excelExportService;


    @Autowired
    public ComputerController(ComputerService computerService, UserService userService, HistoryService historyService,
                              TrashService trashService,ExcelExportService<ComputerOutDTO> excelExportService) {

        this.computerService = computerService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
        this.excelExportService = excelExportService;
    }

    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/all")
    public ResponseEntity<List<ComputerOutDTO>> getAllComputers() {
        List<ComputerOutDTO> computers = computerService.getAllComputersWithDetails();
        return ResponseEntity.ok(computers);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Departments")
    public ResponseEntity<List<ComputerOutDTO>> getByDepartments(@AuthenticationPrincipal UserDetails userDetails) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        Integer deptId = null;
        if (user.isPresent() && user.get().getDepartment() != null) {
            deptId = user.get().getDepartment().getId();
        } else {
            // В случае, если id отдела равен null, вернуть ResponseEntity с соответствующим кодом состояния
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<ComputerOutDTO> computerByDeptId = computerService.getComputersByDept(deptId);
        return ResponseEntity.ok(computerByDeptId);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','manager:read','user:read')")
    @GetMapping("/Details/{computerId}")
    public ResponseEntity<ComputerOutDTO> getComputerDetails(@PathVariable Integer computerId) {
        ComputerOutDTO computerById = computerService.getComputerOutById(computerId);
        return ResponseEntity.ok(computerById);
    }

    @PreAuthorize("hasAnyAuthority('admin:create','management:create')")
    @PostMapping("/add")
    public ResponseEntity<ComputerDTO> addComputer(@RequestBody ComputerDTO computerDTO) {
        ComputerDTO addedComputer = computerService.addComputer(computerDTO);
        return new ResponseEntity<>(addedComputer, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('admin:update','management:update')")
    @PutMapping("/Update/{computerId}")
    public ResponseEntity<ComputerOutDTO> updateComputer(@PathVariable Integer computerId, @AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestBody ComputerDTO computerDTO, @RequestParam(value = "trashdate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date trashdate) {

        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);


        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer equipmentId = computerId;
            Integer itemType = computerDTO.getItemType();

            ComputerDTO originalComputerDTO = computerService.getComputerById(computerId);

            historyService.HistoryObject(originalComputerDTO, computerDTO, equipmentId, itemType, userid);
            //Добавление в таблицу Списания(Trash)
            if (computerDTO.getSpisano() != null && computerDTO.getSpisano().equals(true)) {
                trashService.TrashObject(computerDTO, equipmentId, itemType, trashdate);
            }
            //удаление из таблицы списания
            try {
                if (computerDTO.getSpisano() != null && computerDTO.getSpisano().equals(false)) {
                    trashService.deleteTrashObject(equipmentId, itemType);
                }
            } catch (EntityNotFoundException e) {
                // Обработка исключения, если запись Trash не найдена
            } catch (Exception ex) {
                // Обработка других исключений
            }
            Computer updatedComputer = computerService.updateComputer(computerId, computerDTO);

        }
        return ResponseEntity.ok(computerService.getComputerOutById(computerId));
    }
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/Delete/{computerId}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Integer computerId) {
        Computer deletedComputer = computerService.deleteById(computerId);
        trashService.deleteTrashObject(computerId,deletedComputer.getItemType().getId());
        if (deletedComputer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
    @SneakyThrows
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) {
        List<ComputerOutDTO> computers = computerService.getAllComputersWithDetails();
        excelExportService.exportToExcel(computers,ComputerOutDTO.class,response);
    }


}
