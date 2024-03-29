package com.invent.first.Service;

import com.invent.first.DTO.HistoryDTO;
import com.invent.first.DTO.OutDTO.HistoryOutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoryService {
    HistoryOutDTO getLastHistory(Integer equipmentId, Integer itemtypeId);
    HistoryDTO addHistory(HistoryDTO historyDTO);
    List<HistoryOutDTO> getHistoryList(Integer equipmentId, Integer itemtypeId);
    <T> void HistoryObject(T originalObject, T updatedObject, Integer equipmentId, Integer itemType, Integer userId);

}
