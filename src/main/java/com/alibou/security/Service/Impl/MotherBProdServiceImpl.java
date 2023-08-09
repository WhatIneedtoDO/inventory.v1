package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.MotherBModelDTO;
import com.alibou.security.DTO.MotherBProdDTO;
import com.alibou.security.Entity.MotherBModel;
import com.alibou.security.Entity.MotherBProd;
import com.alibou.security.Repository.MotherBProdRepos;
import com.alibou.security.Service.MotherBProdService;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class MotherBProdServiceImpl implements MotherBProdService {
    private MotherBProdRepos motherBProdRepos;
    @Autowired
    public MotherBProdServiceImpl(MotherBProdRepos motherBProdRepos){
        this.motherBProdRepos = motherBProdRepos;
    }

    @Override
    public List<MotherBProdDTO> getAllMotherBProds() {
        List<MotherBProd> prods = motherBProdRepos.findAll();
        return mapToDTOs(prods);
    }
    private List<MotherBProdDTO> mapToDTOs(List<MotherBProd> models){
        return models.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private MotherBProdDTO mapToDTO(MotherBProd prods){
        return MotherBProdDTO
                .builder()
                .id(prods.getId())
                .name(prods.getName())
                .build();
    }
}
