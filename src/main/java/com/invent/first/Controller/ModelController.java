package com.invent.first.Controller;

import com.invent.first.DTO.ModelDTO;
import com.invent.first.Service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Model")
public class ModelController {
    private ModelService modelService;
    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModelDTO>> getAllCity(){
        List<ModelDTO> models = modelService.getAllModel();
        return ResponseEntity.ok(models);
    }
}
