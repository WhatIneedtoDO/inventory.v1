package com.alibou.security.Service;

import com.alibou.security.DTO.HistoryDTO;
import com.alibou.security.DTO.OutDTO.HistoryOutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoryService {
    HistoryOutDTO getLastHistory(Integer equipmentId, Integer itemtypeId);
    HistoryDTO addHistory(HistoryDTO historyDTO);
    List<HistoryOutDTO> getHistoryList(Integer equipmentId, Integer itemtypeId);
    public <T> void HistoryObject(T originalObject, T updatedObject, Integer equipmentId, Integer itemType, Integer userId);

}
