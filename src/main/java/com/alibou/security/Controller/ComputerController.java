package com.alibou.security.Controller;

import com.alibou.security.DTO.ComputerDTO;
import com.alibou.security.DTO.OutDTO.ComputerOutDTO;
import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Service.ComputerService;
import com.alibou.security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Computers")
public class ComputerController {
    private final ComputerService computerService;
    private final UserService userService;
    @Autowired
    public ComputerController(ComputerService computerService, UserService userService) {
        this.userService = userService;
        this.computerService = computerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComputerOutDTO>> getAllComputers() {
        List<ComputerOutDTO> computers = computerService.getAllComputersWithDetails();
        return ResponseEntity.ok(computers);
    }

    @PostMapping("/add")
    public ResponseEntity<ComputerDTO> addComputer(@RequestBody ComputerDTO computerDTO) {
        ComputerDTO addedComputer = computerService.addComputer(computerDTO);
        return new ResponseEntity<>(addedComputer, HttpStatus.CREATED);
    }

}