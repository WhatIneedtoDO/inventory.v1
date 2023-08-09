package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.CityDTO;
import com.alibou.security.DTO.MotherBModelDTO;
import com.alibou.security.Entity.MotherBModel;
import com.alibou.security.Repository.MotherBModelRepos;
import com.alibou.security.Service.MotherBModelService;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class MotherBModelServiceImpl implements MotherBModelService {
    private MotherBModelRepos motherBModelRepos;
    @Autowired
    public MotherBModelServiceImpl(MotherBModelRepos motherBModelRepos){
        this.motherBModelRepos = motherBModelRepos;
    }

    @Override
    public List<MotherBModelDTO> getAllMotherBModels() {
        List<MotherBModel> models = motherBModelRepos.findAll();
        return mapToDTOs(models);
    }
    private List<MotherBModelDTO> mapToDTOs(List<MotherBModel> models){
        return models.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private MotherBModelDTO mapToDTO(MotherBModel models){
        return MotherBModelDTO
                .builder()
                .id(models.getId())
                .name(models.getName())
                .build();
    }
}
