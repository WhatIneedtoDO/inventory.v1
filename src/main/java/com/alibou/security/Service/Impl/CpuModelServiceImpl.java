package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.CpuModelDTO;
import com.alibou.security.DTO.ModelDTO;
import com.alibou.security.Entity.CpuModel;
import com.alibou.security.Entity.Model;
import com.alibou.security.Repository.CpuModelRepository;
import com.alibou.security.Service.CpuModelService;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class CpuModelServiceImpl implements CpuModelService {
    private final CpuModelRepository cpumodel;

    public CpuModelServiceImpl(CpuModelRepository cpumodel) {
        this.cpumodel = cpumodel;
    }

    @Override
    public List<CpuModelDTO> getAllCpuModel() {
        List<CpuModel> models = cpumodel.findAll();
        return mapToDTOs(models);
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
