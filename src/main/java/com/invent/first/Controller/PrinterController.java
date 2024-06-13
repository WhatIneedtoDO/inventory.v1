package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.DTO.OutDTO.PrinterOutDTO;
import com.invent.first.DTO.PrinterDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.Printers;
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
@RequestMapping("/api/v1/Printer")
public class PrinterController {

    private final PrinterService printerService;
    private final UserService userService;
    private final HistoryService historyService;
    private final TrashService trashService;
    private final ExcelExportService<PrinterOutDTO> excelExportService;

    @Autowired
    public PrinterController(PrinterService printerService, UserService userService, HistoryService historyService, TrashService trashService,
                             ExcelExportService<PrinterOutDTO> excelExportService) {
        this.printerService = printerService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
        this.excelExportService = excelExportService;
    }
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/all")
    public ResponseEntity<List<PrinterOutDTO>> getAllPrinters() {
        List<PrinterOutDTO> printers = printerService.getAllPrintersWithDetails();
        return ResponseEntity.ok(printers);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Details/{printerId}")
    public ResponseEntity<PrinterOutDTO> getPrinterDetails(@PathVariable Integer printerId) {
        PrinterOutDTO printerById = printerService.getPrinterOutById(printerId);
        return ResponseEntity.ok(printerById);
    }
    @PreAuthorize("hasAnyAuthority('boss:read','admin:read','user:read','management:read')")
    @GetMapping("/Departments")
    public ResponseEntity<List<PrinterOutDTO>> getByDepartments(@AuthenticationPrincipal UserDetails userDetails) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        Integer deptId = null;
        if (user.isPresent() && user.get().getDepartment() != null) {
            deptId = user.get().getDepartment().getId();
        } else {
            // В случае, если id отдела равен null, вернуть ResponseEntity с соответствующим кодом состояния
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<PrinterOutDTO> printerByDeptId = printerService.getByDept(deptId);
        return ResponseEntity.ok(printerByDeptId);
    }
    @PreAuthorize("hasAnyAuthority('admin:create','management:create')")
    @PostMapping("/add")
    public ResponseEntity<PrinterDTO> addPrinter(@RequestBody PrinterDTO printerDTO) {
        PrinterDTO addedPrinter = printerService.addPrinter(printerDTO);
        return new ResponseEntity<>(addedPrinter, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('admin:update','management:update')")
    @PutMapping("/Update/{printerId}")
    public ResponseEntity<PrinterOutDTO> updatePrinter(@PathVariable Integer printerId, @AuthenticationPrincipal UserDetails userDetails,
                                                       @RequestBody PrinterDTO printerDTO, @RequestParam(value = "trashdate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date trashdate) {
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);

        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer equipmentId = printerId;
            Integer itemType = printerDTO.getItemType();

            PrinterDTO originalPrinterDTO = printerService.getPrinterById(printerId);

            historyService.HistoryObject(originalPrinterDTO, printerDTO, equipmentId, itemType, userid);

            if (printerDTO.getSpisano() != null && printerDTO.getSpisano().equals(true)) {
                trashService.TrashObject(printerDTO, equipmentId, itemType, trashdate);
            }
            try {
                if (printerDTO.getSpisano() == null || printerDTO.getSpisano().equals(false)) {

                    trashService.deleteTrashObject(equipmentId, itemType);
                }
            } catch (EntityNotFoundException e) {
                // Обработка исключения, если запись Trash не найдена
            } catch (Exception ex) {
                // Обработка других исключений
            }

            Printers updatedPrinter = printerService.updatePrinter(printerId, printerDTO);

        }
        return ResponseEntity.ok(printerService.getPrinterOutById(printerId));
    }
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/Delete/{printerId}")
    public ResponseEntity<Void> deletePrinter(@PathVariable Integer printerId) {
        Printers deletedPrinter = printerService.deleteById(printerId);
        trashService.deleteTrashObject(printerId,deletedPrinter.getItemType().getId());
        if (deletedPrinter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    @SneakyThrows
    @PreAuthorize("hasAnyAuthority('admin:read','management:read')")
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) {
        List<PrinterOutDTO> printers = printerService.getAllPrintersWithDetails();
        excelExportService.exportToExcel(printers, PrinterOutDTO.class, response);
    }

}
