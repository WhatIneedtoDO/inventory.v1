package com.invent.first.Service.Impl;

import com.invent.first.DTO.ItemTypeDTO;
import com.invent.first.Entity.ItemType;
import com.invent.first.Repository.ItemTypeRepository;
import com.invent.first.Service.ItemTypeService;
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
public class ItemTypeServiceImpl implements ItemTypeService {
    private final ItemTypeRepository typesRepository;
    @Autowired
    public ItemTypeServiceImpl(ItemTypeRepository typesRepository){
        this.typesRepository = typesRepository;
    }
    @Override
    public List<ItemTypeDTO> getAllTypes() {
        List<ItemType> typeDTOs = typesRepository.findAll();
        return mapToDTOs(typeDTOs);
    }

    @Override
    public ItemTypeDTO getItemTypeById(Integer id) {
        ItemType itemType = typesRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Item type not found"));
        return mapToDTO(itemType);
    }

    private List<ItemTypeDTO> mapToDTOs(List<ItemType> types){
        return types.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private ItemTypeDTO mapToDTO(ItemType types){
        return ItemTypeDTO
                .builder()
                .id(types.getId())
                .name(types.getName())
                .build();
    }
}
