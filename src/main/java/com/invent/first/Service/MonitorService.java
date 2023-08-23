package com.invent.first.Service;

import com.invent.first.DTO.MonitorDTO;
import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.Entity.Monitor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface MonitorService {
    MonitorDTO addMonitor(MonitorDTO monitorDTO);
    MonitorDTO getMonitorById(Integer monitorId);
    MonitorOutDTO getMonitorOutById(Integer monitorId);
    List<MonitorOutDTO> getAllMonitorsWithDetails();
    Monitor updateMonitor(Integer monitorId, MonitorDTO monitorDTO);

    Monitor deleteById(Integer monitorId);
}
