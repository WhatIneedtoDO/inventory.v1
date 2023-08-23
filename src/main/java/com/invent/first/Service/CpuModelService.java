package com.invent.first.Service;

import com.invent.first.DTO.CpuModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CpuModelService {
    List<CpuModelDTO> getAllCpuModel();
    CpuModelDTO getCpuModelById(Integer id);
}
