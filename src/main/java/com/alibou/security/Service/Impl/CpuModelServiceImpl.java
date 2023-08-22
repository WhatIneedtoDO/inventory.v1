package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.CpuModelDTO;
import com.alibou.security.Entity.CpuModel;
import com.alibou.security.Repository.CpuModelRepository;
import com.alibou.security.Service.CpuModelService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class CpuModelServiceImpl implements CpuModelService {
    private final CpuModelRepository cpuModelRepository;

    public CpuModelServiceImpl(CpuModelRepository cpumodel) {
        this.cpuModelRepository = cpumodel;
    }

    @Override
    public List<CpuModelDTO> getAllCpuModel() {
        List<CpuModel> models = cpuModelRepository.findAll();
        return mapToDTOs(models);
    }

    @Override
    public CpuModelDTO getCpuModelById(Integer id) {
        CpuModel cpuModel = cpuModelRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cpu model not found"));
        return mapToDTO(cpuModel);
    }

    private List<CpuModelDTO> mapToDTOs(List<CpuModel> cpuModels){
        return cpuModels.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private CpuModelDTO mapToDTO(CpuModel cpuModel){
        return CpuModelDTO
                .builder()
                .id(cpuModel.getId())
                .name(cpuModel.getName())
                .build();
    }
}
