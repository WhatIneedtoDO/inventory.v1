package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.HistoryDTO;
import com.alibou.security.DTO.ItemTypeDTO;
import com.alibou.security.DTO.OutDTO.HistoryOutDTO;
import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.HistoryOfChanges;
import com.alibou.security.Entity.ItemType;
import com.alibou.security.Entity.User;
import com.alibou.security.Repository.HistoryRepository;
import com.alibou.security.Repository.ItemTypeRepository;
import com.alibou.security.Repository.UserRepository;
import com.alibou.security.Service.HistoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PersistenceContext
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private HistoryRepository historyRepository;
    private UserRepository userRepository;
    private ItemTypeRepository itemTypeRepository;
    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository,UserRepository userRepository,ItemTypeRepository itemTypeRepository){
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.itemTypeRepository = itemTypeRepository;
    }

    @Override
    public HistoryOutDTO getHistory(Integer equipmentId, Integer itemtypeId) {
        HistoryOfChanges historyOfChanges = historyRepository.findByItemTypeAndEquipment(equipmentId,itemtypeId);
        return mapToDTO(historyOfChanges);
    }

    @Override
    public HistoryDTO addHistory(HistoryDTO historyDTO) {

        HistoryOfChanges historyOfChanges = mapToEntity(historyDTO);
        HistoryOfChanges newHistory = historyRepository.save(historyOfChanges);
        return mapFromDTO(newHistory);
    }

    private HistoryOfChanges mapToEntity(HistoryDTO historyDTO){
        HistoryOfChanges historyOfChanges = new HistoryOfChanges();

        historyOfChanges.setUser(userRepository.findById(historyDTO.getUser())
                .orElseThrow(()-> new EntityNotFoundException("User not found")));
        historyOfChanges.setItemType(itemTypeRepository.findById(historyDTO.getItemtypeId())
                .orElseThrow(()->new EntityNotFoundException("Item type nor found")));
        historyOfChanges.setEquipmentId(historyDTO.getEquipmentId());
        historyOfChanges.setChangeDate(historyDTO.getChangeDate());
        historyOfChanges.setChangeDetails(historyDTO.getChangedetails());
        return historyOfChanges;
    }

    private HistoryOutDTO mapToDTO(HistoryOfChanges historyOfChanges){
       UserDTO user = UserDTO.fromUser(historyOfChanges.getUser());
        ItemTypeDTO itemtype = ItemTypeDTO.fromItemType(historyOfChanges.getItemType());
        return HistoryOutDTO
                .builder()
                .id(historyOfChanges.getId())
                .user(user)
                .itemType(itemtype)
                .equipmentId(historyOfChanges.getEquipmentId())
                .changedate(historyOfChanges.getChangeDate())
                .details(historyOfChanges.getChangeDetails())
                .build();
    }

    private HistoryDTO mapFromDTO(HistoryOfChanges historyOfChanges){
        return HistoryDTO
                .builder()
                .id(historyOfChanges.getId())
                .user(historyOfChanges.getUser().getId())
                .itemtypeId(historyOfChanges.getItemType().getId())
                .equipmentId(historyOfChanges.getEquipmentId())
                .changedetails(historyOfChanges.getChangeDetails())
                .changeDate(historyOfChanges.getChangeDate())
                .build();
    }


}
