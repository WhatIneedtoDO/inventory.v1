package com.invent.first.Service.Impl;

import com.invent.first.DTO.MotherBModelDTO;
import com.invent.first.Entity.MotherBModel;
import com.invent.first.Repository.MotherBModelRepos;
import com.invent.first.Service.MotherBModelService;
import jakarta.persistence.EntityNotFoundException;
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
    private final MotherBModelRepos motherBModelRepos;
    @Autowired
    public MotherBModelServiceImpl(MotherBModelRepos motherBModelRepos){
        this.motherBModelRepos = motherBModelRepos;
    }

    @Override
    public List<MotherBModelDTO> getAllMotherBModels() {
        List<MotherBModel> models = motherBModelRepos.findAll();
        return mapToDTOs(models);
    }

    @Override
    public MotherBModelDTO getMotherModelById(Integer id) {
        MotherBModel model = motherBModelRepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Motherboard model not found"));
        return mapToDTO(model);
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
