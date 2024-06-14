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
    private final MonitorServiceImpl monitorService;
    private final ComputerServiceImpl computerService;
    @Autowired
    public WorkStationService(MonitorServiceImpl monitorService,ComputerServiceImpl computerService){
        this.monitorService = monitorService;
        this.computerService = computerService;
    }
    //объединяет 2 метода из ComputerServiceImpl и MonitorServiceImpl, формирует список с объектами на вывод
    public List<Object> getAllWorkstations (){
        List<ComputerOutDTO> computers = computerService.getAllComputerPairs();
        List<MonitorOutDTO> monitors = monitorService.getAllMonitorPairs();
        if (computers.isEmpty() || monitors.isEmpty()) {
            return Collections.emptyList();
        }
        //заполянет лист по 2 элемента (компьютер\монитор)
        List<Object> workstations = new ArrayList<>();
        Iterator<ComputerOutDTO> computerIterator = computers.iterator();
        Iterator<MonitorOutDTO> monitorIterator = monitors.iterator();
        while (computerIterator.hasNext() && monitorIterator.hasNext()) {
            workstations.add(computerIterator.next());
            workstations.add(monitorIterator.next());
        }
        return workstations;
    }

    public List<Object> getWorkstationsByDept (Integer deptId){
        List<ComputerOutDTO> computersByDept = computerService.getComputersByDept(deptId);
        List<MonitorOutDTO> monitorsByDept = monitorService.getByDept(deptId);
        if (computersByDept.isEmpty() || monitorsByDept.isEmpty()) {
            return Collections.emptyList();
        }
        List<Object> workstations = new ArrayList<>();
        Iterator<ComputerOutDTO> computerDeptIterator = computersByDept.iterator();
        Iterator<MonitorOutDTO> monitorDeptIterator = monitorsByDept.iterator();
        while (computerDeptIterator.hasNext() && monitorDeptIterator.hasNext()) {
            workstations.add(computerDeptIterator.next());
            workstations.add(monitorDeptIterator.next());
        }
        return workstations;
    }

}
