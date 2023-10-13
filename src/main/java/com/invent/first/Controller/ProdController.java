package com.invent.first.Controller;

import com.invent.first.DTO.ProductionsDTO;
import com.invent.first.Service.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Production")
public class ProdController {
    private final ProdService prodService;
    @Autowired
    public ProdController(ProdService prodService){
        this.prodService = prodService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductionsDTO>> getAllProds(){
        List<ProductionsDTO> prods = prodService.getAllProds();
        return ResponseEntity.ok(prods);
    }
}
