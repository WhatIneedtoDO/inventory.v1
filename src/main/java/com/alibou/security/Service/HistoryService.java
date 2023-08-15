package com.alibou.security.Service;

import com.alibou.security.DTO.HistoryDTO;
import com.alibou.security.DTO.ItemTypeDTO;
import com.alibou.security.DTO.OutDTO.HistoryOutDTO;
import org.springframework.stereotype.Service;

@Service
public interface HistoryService {
    HistoryOutDTO getHistory(Integer equipmentId, Integer itemtypeId);
    HistoryDTO addHistory(HistoryDTO historyDTO);

}
