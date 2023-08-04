package com.alibou.security.Service;

import com.alibou.security.DTO.ItemTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemTypeService {
    List<ItemTypeDTO> getAllTypes();
}
