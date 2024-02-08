package com.invent.first.Controller;

import com.invent.first.DTO.DepartmentsDTO;
import com.invent.first.Service.DepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Departments")
public class DepartmentsController {
    private final DepsService depsService;
    @Autowired
    public DepartmentsController(DepsService depsService) {
        this.depsService = depsService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<DepartmentsDTO>> getAllDepartments(){
        List<DepartmentsDTO> deps = depsService.getAllDeps();
        return ResponseEntity.ok(deps);
    }
}
