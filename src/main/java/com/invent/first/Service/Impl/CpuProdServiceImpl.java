package com.invent.first.Service.Impl;

import com.invent.first.DTO.CpuProductionDTO;
import com.invent.first.Entity.CpuProduction;
import com.invent.first.Repository.CpuProdRepository;
import com.invent.first.Service.CpuProdService;
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
