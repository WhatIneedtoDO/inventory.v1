package com.invent.first.Controller;

import com.invent.first.Service.TrashService;
import com.invent.first.response.TrashJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Trash")
public class TrashController {
    private final TrashService trashService;
    @Autowired
    public TrashController(TrashService trashService){
        this.trashService = trashService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TrashJsonResponse>> getAllTrash(){
        List<TrashJsonResponse> trashList = trashService.getList();
        return ResponseEntity.ok(trashList);
    }
}
