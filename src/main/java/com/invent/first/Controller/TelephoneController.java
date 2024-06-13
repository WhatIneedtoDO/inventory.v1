package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.DTO.OutDTO.TelephoneOutDTO;
import com.invent.first.DTO.TelephoneDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.Telephones;
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
@RequestMapping("/api/v1/Telephones")
public class TelephoneController {
    private final TelephoneService telephoneService;
    private final UserService userService;
    private final HistoryService historyService;
    private final TrashService trashService;

    private final ExcelExportService<TelephoneOutDTO> excelExportService;
    @Autowired
    public TelephoneController (TelephoneService telephoneService, UserService userService, HistoryService historyService,TrashService trashService,
                                ExcelExportService<TelephoneOutDTO> excelExportService){
        this.telephoneService = telephoneService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
        this.excelExportService = excelExportService;
    }
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/all")
    public ResponseEntity<List<TelephoneOutDTO>> getAllTelephones(){
        List<TelephoneOutDTO> telephones = telephoneService.getAllTelephonesWithDetails();
        return ResponseEntity.ok(telephones);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Details/{telephoneId}")
    public ResponseEntity<TelephoneOutDTO> getTelephoneDetails(@PathVariable Integer telephoneId){
        TelephoneOutDTO telephoneById = telephoneService.getTelephoneOutById(telephoneId);
        return ResponseEntity.ok(telephoneById);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Departments")
    public ResponseEntity<List<TelephoneOutDTO>> getByDepartments(@AuthenticationPrincipal UserDetails userDetails) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        Integer deptId = null;
        if (user.isPresent() && user.get().getDepartment() != null) {
            deptId = user.get().getDepartment().getId();
        } else {
            // В случае, если id отдела равен null, вернуть ResponseEntity с соответствующим кодом состояния
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<TelephoneOutDTO> telephoneByDeptId = telephoneService.getByDept(deptId);
        return ResponseEntity.ok(telephoneByDeptId);
    }
    @PreAuthorize("hasAnyAuthority('admin:create','management:create')")
    @PostMapping("/add")
    public ResponseEntity<TelephoneDTO> addTelephone (@RequestBody TelephoneDTO telephoneDTO){
        TelephoneDTO addedTelephone = telephoneService.addTelephone(telephoneDTO);
        return new ResponseEntity<>(addedTelephone, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('admin:update','management:update')")
    @PutMapping("/Update/{telephoneId}")
    public ResponseEntity<TelephoneOutDTO> updateTelephone(@PathVariable Integer telephoneId, @AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody TelephoneDTO telephoneDTO,@RequestParam(value = "trashdate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date trashdate){

        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);


        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer itemType = telephoneDTO.getItemType();

            TelephoneDTO originalTelephoneDTO = telephoneService.getTelephoneById(telephoneId);
            historyService.HistoryObject(originalTelephoneDTO, telephoneDTO, telephoneId, itemType, userid);

            if (telephoneDTO.getSpisano()!=null && telephoneDTO.getSpisano().equals(true)){
                trashService.TrashObject(telephoneDTO,telephoneId,itemType,trashdate);
            }
            try {
            if (telephoneDTO.getSpisano() == null || telephoneDTO.getSpisano().equals(false)){
                    trashService.deleteTrashObject(telephoneId, itemType);
            }

            } catch (EntityNotFoundException e) {
                // Обработка исключения, если запись Trash не найдена
            } catch (Exception ex) {
                // Обработка других исключений
            }

            Telephones updatedTelephone = telephoneService.updateTelephone(telephoneId, telephoneDTO);
        }
        return ResponseEntity.ok(telephoneService.getTelephoneOutById(telephoneId));
    }
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/Delete/{telephoneId}")
    public ResponseEntity<Void> delete(@PathVariable Integer telephoneId){
        Telephones deletedTelephone = telephoneService.deleteById(telephoneId);
        trashService.deleteTrashObject(telephoneId,deletedTelephone.getItemType().getId());
        if (deletedTelephone == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    @SneakyThrows
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) {
        List<TelephoneOutDTO> telephones = telephoneService.getAllTelephonesWithDetails();
        excelExportService.exportToExcel(telephones, TelephoneOutDTO.class, response);
    }
}
