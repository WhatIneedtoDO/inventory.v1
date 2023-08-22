package com.alibou.security.Service;

import com.alibou.security.DTO.CpuProductionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CpuProdService {
    List<CpuProductionDTO> getAllCpuProduction();
    CpuProductionDTO getCpuProdById(Integer id);
}
