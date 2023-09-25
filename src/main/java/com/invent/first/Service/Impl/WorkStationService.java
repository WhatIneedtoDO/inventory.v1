package com.invent.first.Service.Impl;

import com.invent.first.DTO.OutDTO.ComputerOutDTO;
import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
@PersistenceContext
@Transactional
public class WorkStationService {
    private MonitorServiceImpl monitorService;
    private ComputerServiceImpl computerService;
    @Autowired
    public WorkStationService(MonitorServiceImpl monitorService,ComputerServiceImpl computerService){
        this.monitorService = monitorService;
        this.computerService = computerService;
    }

    public List<Object> getAllWorkstations (){
        List<ComputerOutDTO> computers = computerService.getAllComputerPairs();
        List<MonitorOutDTO> monitors = monitorService.getAllMonitorPairs();
        if (computers.isEmpty() || monitors.isEmpty()) {
            return Collections.emptyList();
        }

        List<Object> workstations = new ArrayList<>();
        Iterator<ComputerOutDTO> computerIterator = computers.iterator();
        Iterator<MonitorOutDTO> monitorIterator = monitors.iterator();
        while (computerIterator.hasNext() && monitorIterator.hasNext()) {
            workstations.add(computerIterator.next());
            workstations.add(monitorIterator.next());
        }
        return workstations;
    }

}
