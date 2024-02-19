package com.invent.first.Service;

import com.invent.first.DTO.ComputerDTO;
import com.invent.first.DTO.OutDTO.ComputerOutDTO;
import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.DTO.OutDTO.TelephoneOutDTO;
import com.invent.first.DTO.TelephoneDTO;
import com.invent.first.Entity.Computer;
import com.invent.first.Entity.Telephones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TelephoneService {
    TelephoneDTO getTelephoneById(Integer telephoneId);
    TelephoneOutDTO getTelephoneOutById(Integer telephoneId);
    Telephones updateTelephone(Integer telephoneId, TelephoneDTO telephoneDTO);
    TelephoneDTO addTelephone(TelephoneDTO telephoneDTO);
    List<TelephoneOutDTO> getAllTelephonesWithDetails();
    List<TelephoneOutDTO> getByDept(Integer deptId);
    Telephones deleteById(Integer telephoneId);
}
