package com.alibou.security.Controller;

import com.alibou.security.DTO.ComputerDTO;
import com.alibou.security.DTO.HistoryDTO;
import com.alibou.security.DTO.OutDTO.ComputerOutDTO;
import com.alibou.security.DTO.OutDTO.HistoryOutDTO;
import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.Computer;
import com.alibou.security.Service.ComputerService;
import com.alibou.security.Service.HistoryService;
import com.alibou.security.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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


    @Autowired
    public ComputerController(ComputerService computerService, UserService userService, HistoryService historyService) {

        this.computerService = computerService;
        this.userService = userService;
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComputerOutDTO>> getAllComputers() {
        List<ComputerOutDTO> computers = computerService.getAllComputersWithDetails();
        return ResponseEntity.ok(computers);
    }

    @GetMapping("/Details/{computerId}")
    public ResponseEntity<ComputerOutDTO> getComputerDetails(@PathVariable Integer computerId) {
        ComputerOutDTO computerById = computerService.getComputerOutById(computerId);
        return ResponseEntity.ok(computerById);
    }

    @PostMapping("/add")
    public ResponseEntity<ComputerDTO> addComputer(@RequestBody ComputerDTO computerDTO) {
        ComputerDTO addedComputer = computerService.addComputer(computerDTO);
        return new ResponseEntity<>(addedComputer, HttpStatus.CREATED);
    }

    @PutMapping("/Update/{computerId}")
    public ResponseEntity<ComputerOutDTO> updateComputer(@PathVariable Integer computerId, @AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestBody ComputerDTO computerDTO) {

        var username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);


        if (user.isPresent()) {
            Integer userid = user.get().getId();
            Integer equipmentId = computerId;
            Integer itemType = computerDTO.getItemType();

            ComputerDTO originalComputerDTO = computerService.getComputerById(computerId);
            List<String> changes = new ArrayList<>();

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Field[] fields = originalComputerDTO.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object originalValue = field.get(originalComputerDTO);
                    Object newValue = field.get(computerDTO);
                    if (originalValue != null && !originalValue.equals(newValue)) {
                        if (originalValue instanceof Date && newValue instanceof Date) {

                            String originalDateStr = dateFormatter.format((Date) originalValue);
                            String newDateStr = dateFormatter.format((Date) newValue);

                            if (!originalDateStr.equals(newDateStr)) {
                                changes.add(field.getName() + " = " + originalDateStr + ",= " + newDateStr);
                            }
                        } else {
                            changes.add(field.getName() + " = " + originalValue + ",= " + newValue);
                        }
                    }
                } catch (IllegalAccessException e) {
                }
            }

            String changeDetails = String.join(";", changes);

            HistoryDTO historyDTO = HistoryDTO.builder()
                    .user(userid)
                    .itemtypeId(itemType)
                    .equipmentId(equipmentId)
                    .changeDate(new Date())
                    .changedetails(changeDetails)
                    .build();

            historyService.addHistory(historyDTO);
            Computer updatedComputer = computerService.updateComputer(computerId, computerDTO);

        }



        return ResponseEntity.ok(computerService.getComputerOutById(computerId));
    }

   @DeleteMapping("/Delete/{computerId}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Integer computerId) {
        Computer deletedComputer = computerService.deleteById(computerId);

        if (deletedComputer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }



}
