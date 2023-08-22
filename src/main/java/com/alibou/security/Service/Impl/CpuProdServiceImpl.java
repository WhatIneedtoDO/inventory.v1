package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.CpuProductionDTO;
import com.alibou.security.Entity.CpuProduction;
import com.alibou.security.Repository.CpuProdRepository;
import com.alibou.security.Service.CpuProdService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class CpuProdServiceImpl implements CpuProdService {
    private final CpuProdRepository prodRepository;

    public CpuProdServiceImpl(CpuProdRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    @Override
    public List<CpuProductionDTO> getAllCpuProduction() {
        List<CpuProduction> productions = prodRepository.findAll();
        return mapToDTOs(productions);
    }

    @Override
    public CpuProductionDTO getCpuProdById(Integer id) {
        CpuProduction cpuProduction = prodRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cpu production not found"));
        return mapToDTO(cpuProduction);
    }

    private List<CpuProductionDTO> mapToDTOs(List<CpuProduction> productions) {
        return productions.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private CpuProductionDTO mapToDTO(CpuProduction cpuProduction) {
        return CpuProductionDTO
                .builder()
                .id(cpuProduction.getId())
                .name(cpuProduction.getName())
                .build();
    }
}
