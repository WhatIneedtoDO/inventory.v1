package com.alibou.security.Service;

import com.alibou.security.DTO.MonitorDTO;
import com.alibou.security.DTO.OutDTO.MonitorOutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MonitorService {

    MonitorDTO addMonitor(MonitorDTO monitorDTO);
    List<MonitorOutDTO> getAllMonitorsWithDetails();
}
