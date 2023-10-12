package com.invent.first.Service;

import com.invent.first.DTO.TrashDTO;
import com.invent.first.Entity.Trash;
import com.invent.first.response.TrashJsonResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TrashService {
    TrashDTO add(TrashDTO trashDTO);
   List<TrashJsonResponse> getList();
    public <T> void TrashObject(T objectDTO,Integer equipmentId, Integer itemType, Date trashDate);
    void deleteTrashObject(Integer equipmentId, Integer itemTypeId);

    Trash getTrash(Integer equipmentId,Integer itemTypeId);
}
