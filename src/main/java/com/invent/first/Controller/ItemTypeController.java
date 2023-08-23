package com.invent.first.Controller;

import com.invent.first.DTO.ItemTypeDTO;
import com.invent.first.Service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Type")
public class ItemTypeController {
    private ItemTypeService itemTypeService;
    @Autowired
    public ItemTypeController(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<ItemTypeDTO>> getAllTypes(){
        List<ItemTypeDTO> types = itemTypeService.getAllTypes();
        return ResponseEntity.ok(types);
    }
}
