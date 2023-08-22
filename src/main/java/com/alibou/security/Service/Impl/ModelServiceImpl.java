package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.ModelDTO;
import com.alibou.security.Entity.Model;
import com.alibou.security.Repository.ModelRepository;
import com.alibou.security.Service.ModelService;
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
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository){this.modelRepository = modelRepository;}
    @Override
    public List<ModelDTO> getAllModel() {
        List<Model> models = modelRepository.findAll();
        return mapToDTOs(models);
    }

    @Override
    public ModelDTO getModelById(Integer id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Model not found"));
        return mapToDTO(model);
    }

    private List<ModelDTO> mapToDTOs(List<Model> models){
        return models.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ModelDTO mapToDTO(Model model){
        return ModelDTO
                .builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}
