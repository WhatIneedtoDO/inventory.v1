package com.alibou.security.Controller;

import com.alibou.security.DTO.MotherBModelDTO;
import com.alibou.security.DTO.MotherBProdDTO;
import com.alibou.security.Service.MotherBModelService;
import com.alibou.security.Service.MotherBProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/public")
public class PublicController {
    private final MotherBProdService motherBProdService;
    private final MotherBModelService motherBModelService;
    @Autowired
    public PublicController(MotherBProdService motherBProdService,MotherBModelService motherBModelService){
        this.motherBProdService = motherBProdService ;
        this.motherBModelService = motherBModelService;
    }
    @GetMapping("/MotherBoard/Productions")
    public ResponseEntity<List<MotherBProdDTO>> getAllProd(){
        List<MotherBProdDTO> prods = motherBProdService.getAllMotherBProds();
        return ResponseEntity.ok(prods);
    }
    @GetMapping("/MotherBoard/Models")
    public ResponseEntity<List<MotherBModelDTO>> getAllModel(){
        List<MotherBModelDTO> models = motherBModelService.getAllMotherBModels();
        return ResponseEntity.ok(models);
    }
}
