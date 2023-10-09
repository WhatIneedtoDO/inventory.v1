package com.invent.first.Service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.first.response.TrashJsonResponse;
import com.invent.first.DTO.TrashDTO;
import com.invent.first.Entity.Trash;
import com.invent.first.Repository.ItemTypeRepository;
import com.invent.first.Repository.TrashRepository;
import com.invent.first.Service.TrashService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@PersistenceContext
@Transactional
public class TrashServiceImpl implements TrashService {
    private final TrashRepository trashRepository;
    private final ItemTypeRepository itemTypeRepository;

    public TrashServiceImpl(TrashRepository trashRepository, ItemTypeRepository itemTypeRepository) {
        this.trashRepository = trashRepository;
        this.itemTypeRepository = itemTypeRepository;
    }


    //Формирование объекта TrashDTO
    // id,itemtype - берется из объекта такие же как и в объекте ДТО,который пришел,дата ставится вручную
    public <T> void TrashObject(T objectDTO, Integer equipmentId, Integer itemType, Date trashDate) {
        Field[] dtoFields = objectDTO.getClass().getDeclaredFields();
        for (Field field : dtoFields) {
            field.setAccessible(true);
            try {
                //проверка на наличие поля spisano у объекта и равняется ли оно true
                if (field.getName().equals("spisano") && (Boolean) field.get(objectDTO)) {
                    TrashDTO trashDTO = TrashDTO.builder()
                            .itemtypeId(itemType)
                            .equipmentId(equipmentId)
                            .trashDate(trashDate)
                            .build();
                    add(trashDTO);
                }
            } catch (IllegalAccessException e) {
                // Обработка исключения
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteTrashObject(Integer equipmentId, Integer itemTypeId){
        Optional<Trash> trashOptional = trashRepository.findByEquipmentIdAndItemTypeId(equipmentId, itemTypeId);
        if (trashOptional.isPresent()) {
            Trash trashToDelete = trashOptional.get();
            trashRepository.delete(trashToDelete);
        }
    }

    @Override
    public TrashDTO add(TrashDTO trashDTO) {
        Trash trash = mapToEntity(trashDTO);
        Trash newTrash = trashRepository.save(trash);
        return mapFromDTO(newTrash);
    }

    private TrashDTO mapFromDTO(Trash trash) {
        return TrashDTO
                .builder()
                .id(trash.getId())
                .equipmentId(trash.getEquipment_id())
                .itemtypeId(trash.getItemType().getId())
                .trashDate(trash.getTrashDate())
                .build();
    }

    private Trash mapToEntity(TrashDTO trashDTO) {
        Trash trash = new Trash();

        trash.setEquipment_id(trashDTO.getEquipmentId());
        trash.setItemType(itemTypeRepository.findById(trashDTO.getItemtypeId())
                .orElseThrow(() -> new EntityNotFoundException("Item type not found")));
        trash.setTrashDate(trashDTO.getTrashDate());
        return trash;
    }

    @Override
    public List<TrashJsonResponse> getList() {
        List<Tuple> tuples = trashRepository.getAllTrash();
        List<TrashJsonResponse> jsonResponseList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            jsonResponseList.add(new TrashJsonResponse(tuple));
        }

        return jsonResponseList;
    }
}
