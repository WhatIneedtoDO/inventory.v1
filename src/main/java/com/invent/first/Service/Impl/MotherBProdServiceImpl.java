package com.invent.first.Service.Impl;

import com.invent.first.DTO.MotherBProdDTO;
import com.invent.first.Entity.MotherBProd;
import com.invent.first.Repository.MotherBProdRepos;
import com.invent.first.Service.MotherBProdService;
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
public class MotherBProdServiceImpl implements MotherBProdService {
    private final MotherBProdRepos motherBProdRepos;
    @Autowired
    public MotherBProdServiceImpl(MotherBProdRepos motherBProdRepos){
        this.motherBProdRepos = motherBProdRepos;
    }

    @Override
    public List<MotherBProdDTO> getAllMotherBProds() {
        List<MotherBProd> prods = motherBProdRepos.findAll();
        return mapToDTOs(prods);
    }

    @Override
    public MotherBProdDTO getMotherBProdById(Integer id) {
        MotherBProd motherBProd = motherBProdRepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Motherboard production not found"));
        return mapToDTO(motherBProd);
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
