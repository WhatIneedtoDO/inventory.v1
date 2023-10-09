package com.invent.first.Controller;

import com.invent.first.DTO.CpuModelDTO;
import com.invent.first.DTO.CpuProductionDTO;
import com.invent.first.DTO.MotherBModelDTO;
import com.invent.first.DTO.MotherBProdDTO;
import com.invent.first.DTO.OutDTO.HistoryOutDTO;
import com.invent.first.Service.*;
import com.invent.first.response.TrashJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/public")
public class PublicController {
    private final MotherBProdService motherBProdService;
    private final MotherBModelService motherBModelService;
    private final CpuProdService cpuProdService;
    private final CpuModelService cpuModelService;
    private final HistoryService historyService;
    private final TrashService trashService;
    @Autowired
    public PublicController(MotherBProdService motherBProdService, MotherBModelService motherBModelService,
                            CpuProdService cpuProdService, CpuModelService cpuModelService, HistoryService historyService,
                            TrashService trashService){
        this.motherBProdService = motherBProdService ;
        this.motherBModelService = motherBModelService;
        this.cpuProdService = cpuProdService;
        this.cpuModelService = cpuModelService;
        this.historyService = historyService;
        this.trashService = trashService;
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
    @GetMapping("/Cpu/Productions")
    public ResponseEntity<List<CpuProductionDTO>> getAllCpuProd(){
        List<CpuProductionDTO> cpuProductions =cpuProdService.getAllCpuProduction();
        return ResponseEntity.ok(cpuProductions);
    }

    @GetMapping("/Cpu/Models")
    public ResponseEntity<List<CpuModelDTO>> getAllCpuModels(){
        List<CpuModelDTO> cpuModels = cpuModelService.getAllCpuModel();
        return ResponseEntity.ok(cpuModels);
    }

    @GetMapping("/History/{equipmentId}/{itemtypeId}")
    public ResponseEntity<List<HistoryOutDTO>> getAllHistory(@PathVariable Integer equipmentId, @PathVariable Integer itemtypeId) {
        List<HistoryOutDTO> history = historyService.getHistoryList(equipmentId, itemtypeId);
        return ResponseEntity.ok(history);
    }
    @GetMapping("/History/Last/{equipmentId}/{itemtypeId}")
    public ResponseEntity<HistoryOutDTO> getLastHistory(@PathVariable Integer equipmentId, @PathVariable Integer itemtypeId) {
        HistoryOutDTO history = historyService.getLastHistory(equipmentId, itemtypeId);
        return ResponseEntity.ok(history);
    }
    @GetMapping("/Trash/all")
    public ResponseEntity<List<TrashJsonResponse>> getAllTrash(){
        List<TrashJsonResponse> trashList = trashService.getList();
        return ResponseEntity.ok(trashList);
    }
}
