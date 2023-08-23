package com.invent.first.Service;

import com.invent.first.DTO.CpuProductionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CpuProdService {
    List<CpuProductionDTO> getAllCpuProduction();
    CpuProductionDTO getCpuProdById(Integer id);
}
