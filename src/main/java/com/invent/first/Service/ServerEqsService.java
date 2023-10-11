package com.invent.first.Service;

import com.invent.first.DTO.OutDTO.ServerEqsOutDTO;
import com.invent.first.DTO.ServerEqsDTO;
import com.invent.first.Entity.ServerEqs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServerEqsService {
    ServerEqsDTO getServerEqsById(Integer eqsId);
    ServerEqsOutDTO getServerEqsOutById(Integer eqsId);
    ServerEqs updateServerEqs(Integer eqsId,ServerEqsDTO serverEqsDTO);
    ServerEqsDTO addServerEqs(ServerEqsDTO serverEqsDTO);
    List<ServerEqsOutDTO> getAllServerEqsWithDetails();
    ServerEqs deleteById(Integer eqsId);
}
