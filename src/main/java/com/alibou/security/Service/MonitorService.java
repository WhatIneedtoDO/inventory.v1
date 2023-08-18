package com.alibou.security.Service;

import com.alibou.security.DTO.MonitorDTO;
import com.alibou.security.DTO.OutDTO.MonitorOutDTO;
import com.alibou.security.Entity.Monitor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface MonitorService {
    MonitorDTO addMonitor(MonitorDTO monitorDTO);
    MonitorDTO getMonitorById(Integer monitorId);
    MonitorOutDTO getMonitorOutById(Integer monitorId);
    List<MonitorOutDTO> getAllMonitorsWithDetails();
    Monitor updateMonitor(Integer monitorId,MonitorDTO monitorDTO);

    Monitor deleteById(Integer monitorId);
}
