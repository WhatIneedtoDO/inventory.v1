package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.PowerSystemOutDTO;
import com.invent.first.DTO.PowerSystemDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.PowerSystem;
import com.invent.first.Service.HistoryService;
import com.invent.first.Service.PowerSystemService;
import com.invent.first.Service.TrashService;
import com.invent.first.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/PowerSystems")
public class PowerSysController {
    private final PowerSystemService powerSystemService;
    private final UserService userService;
    private final HistoryService historyService;
    private final TrashService trashService;
    @Autowired
    public PowerSysController(PowerSystemService powerSystemService, UserService userService,
                              HistoryService historyService, TrashService trashService) {
        this.powerSystemService = powerSystemService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<PowerSystemOutDTO>> getAllPowerSys(){
        List<PowerSystemOutDTO> powerSys = powerSystemService.getAllPSWithDetails();
        return ResponseEntity.ok(powerSys);
    }
    @GetMapping("/Details/{psId}")
    public ResponseEntity<PowerSystemOutDTO> getPowerSysDetails(@PathVariable Integer psId){
        PowerSystemOutDTO powerSystemById = powerSystemService.getPSOutById(psId);
        return ResponseEntity.ok(powerSystemById);
    }
    @PostMapping("/add")
    public ResponseEntity<PowerSystemDTO> addPowerSys(@RequestBody PowerSystemDTO powerSystemDTO){
        PowerSystemDTO addedPowerSys = powerSystemService.addPowerSystem(powerSystemDTO);
        return new ResponseEntity<>(addedPowerSys, HttpStatus.CREATED);
    }
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
    @DeleteMapping("/Delete/{psId}")
    public ResponseEntity<Void> deletePowerSys(@PathVariable Integer psId){
        PowerSystem deletedPowerSys = powerSystemService.deleteById(psId);
        trashService.deleteTrashObject(psId,deletedPowerSys.getItemType().getId());
        if (deletedPowerSys == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
