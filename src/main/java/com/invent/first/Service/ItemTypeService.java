package com.invent.first.Service;

import com.invent.first.DTO.ItemTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemTypeService {
    List<ItemTypeDTO> getAllTypes();
    ItemTypeDTO getItemTypeById(Integer id);
}
