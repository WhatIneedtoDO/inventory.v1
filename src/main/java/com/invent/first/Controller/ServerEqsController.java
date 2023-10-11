package com.invent.first.Controller;

import com.invent.first.DTO.OutDTO.ServerEqsOutDTO;
import com.invent.first.DTO.ServerEqsDTO;
import com.invent.first.DTO.TelephoneDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.Enum.RAM;
import com.invent.first.Entity.ServerEqs;
import com.invent.first.Entity.Telephones;
import com.invent.first.Service.HistoryService;
import com.invent.first.Service.ServerEqsService;
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
@RequestMapping("/api/v1/ServerEquipments")
public class ServerEqsController {
    private final ServerEqsService serverEqsService;
    private final UserService userService;
    private final HistoryService historyService;
    private final TrashService trashService;

    @Autowired
    public ServerEqsController(ServerEqsService serverEqsService, UserService userService,
                               HistoryService historyService, TrashService trashService) {
        this.serverEqsService = serverEqsService;
        this.userService = userService;
        this.historyService = historyService;
        this.trashService = trashService;
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
        if (deletedServerEqs == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
