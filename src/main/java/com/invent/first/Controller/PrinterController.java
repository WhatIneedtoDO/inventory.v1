package com.invent.first.Controller;
import com.invent.first.DTO.OutDTO.PrinterOutDTO;
import com.invent.first.DTO.PrinterDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.Printers;
import com.invent.first.Service.HistoryService;
import com.invent.first.Service.PrinterService;
import com.invent.first.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Printer")
public class PrinterController {

    private final PrinterService printerService;
    private final UserService userService;
    private final HistoryService historyService;

    @Autowired
    public PrinterController(PrinterService printerService, UserService userService,HistoryService historyService){
        this.printerService = printerService;
        this.userService = userService;
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PrinterOutDTO>> getAllPrinters(){
        List<PrinterOutDTO> printers = printerService.getAllPrintersWithDetails();
        return ResponseEntity.ok(printers);
    }
    @GetMapping("/Details/{printerId}")
    public ResponseEntity<PrinterOutDTO> getPrinterDetails(@PathVariable Integer printerId){
        PrinterOutDTO printerById = printerService.getPrinterOutById(printerId);
        return ResponseEntity.ok(printerById);
    }
    @PostMapping("/add")
    public ResponseEntity<PrinterDTO> addPrinter(@RequestBody PrinterDTO printerDTO){
        PrinterDTO addedPrinter = printerService.addPrinter(printerDTO);
        return new ResponseEntity<>(addedPrinter, HttpStatus.CREATED);
    }
    @PutMapping("/Update/{printerId}")
    public ResponseEntity<PrinterOutDTO> updatePrinter(@PathVariable Integer printerId,@AuthenticationPrincipal UserDetails userDetails,
                                                       @RequestBody PrinterDTO printerDTO){
        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);

        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer equipmentId = printerId;
            Integer itemType = printerDTO.getItemType();

            PrinterDTO originalPrinterDTO = printerService.getPrinterById(printerId);

            historyService.HistoryObject(originalPrinterDTO, printerDTO, equipmentId, itemType, userid);

            Printers updatedPrinter = printerService.updatePrinter(printerId, printerDTO);

        }
        return ResponseEntity.ok(printerService.getPrinterOutById(printerId));
    }
    @DeleteMapping("/Delete/{printerId}")
    public ResponseEntity<Void> deletePrinter(@PathVariable Integer printerId){
        Printers deletedPrinter = printerService.deleteById(printerId);
        if ( deletedPrinter == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
