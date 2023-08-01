package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.*;
import com.alibou.security.DTO.OutDTO.ComputerOutDTO;
import com.alibou.security.DTO.OutDTO.MonitorOutDTO;
import com.alibou.security.Entity.Computer;
import com.alibou.security.Entity.Monitor;
import com.alibou.security.Entity.User;
import com.alibou.security.Repository.*;
import com.alibou.security.Service.MonitorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class MonitorServiceImpl implements MonitorService {
    private MonitorRepository monitorRepository;
    private UserRepository userRepository;
    private ProductionRepository productionRepository;
    private ModelRepository modelRepository;
    private LocationRepository locationRepository;
    private ItemTypeRepository itemTypeRepository;
    private CityRepository cityRepository;
    @Autowired
    public MonitorServiceImpl(MonitorRepository monitorRepository, UserRepository userRepository, ProductionRepository productionRepository,
                              ModelRepository modelRepository, LocationRepository locationRepository, ItemTypeRepository itemTypeRepository, CityRepository cityRepository){

        this.monitorRepository = monitorRepository;
        this.productionRepository = productionRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.cityRepository = cityRepository;

    }

    //добавление компьютера только с ID в JSON объекте (вывод JSON объекта только с Id присоединенных столбцов )
    @Override
    public MonitorDTO addMonitor(MonitorDTO monitorDTO) {
        Monitor monitor = mapToEntity(monitorDTO);
        Monitor savedMonitor = monitorRepository.save(monitor);
        return mapToDTOWithId(savedMonitor);
    }

    //вывод JSON объекта с полноценными объектами вместо Id
    @Override
    public List<MonitorOutDTO> getAllMonitorsWithDetails() {
        List<Monitor> monitors = monitorRepository.findAllMonitorsWithDetails();
        return mapToDTOs(monitors);
    }

    private List<MonitorOutDTO> mapToDTOs(List<Monitor> monitors) {
        return monitors.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    //ComputerOutDTO для вытаскивания из базы полноценных JSON объектов ,а не только ID
    private MonitorOutDTO mapToDTO(Monitor monitor) {
        UserDTO userDTO = UserDTO.fromUser(monitor.getUser());
        ProductionsDTO productionsDTO = ProductionsDTO.fromProductions(monitor.getProduction());
        ModelDTO modelDTO = ModelDTO.fromModel(monitor.getModel());
        ItemTypeDTO itemTypeDTO = ItemTypeDTO.fromItemType(monitor.getItemType());
        CityDTO cityDTO = CityDTO.fromCity(monitor.getCity());
        LocationDTO locationDTO = LocationDTO.fromLocation(monitor.getLocation());
        return MonitorOutDTO
                .builder()
                .id(monitor.getId())
                .i_card(monitor.getI_card())
                .serialnumber(monitor.getSerialnumber())
                .i_number(monitor.getI_number())
                .production(productionsDTO)
                .model(modelDTO)
                .itemType(itemTypeDTO)
                .size(monitor.getSize())
                .year(monitor.getYear())
                .serv(monitor.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(monitor.getRoom())
                .userId(userDTO)
                .staydate(monitor.getStaydate())
                .price(monitor.getPrice())
                .comment(monitor.getComment())
                .build();

    }

    private MonitorDTO mapToDTOWithId(Monitor monitor) {

        return MonitorDTO
                .builder()
                .id(monitor.getId())
                .i_card(monitor.getI_card())
                .serialnumber(monitor.getSerialnumber())
                .i_number(monitor.getI_number())
                .production(monitor.getProduction().getId())
                .model(monitor.getModel().getId())
                .itemType(monitor.getItemType().getId())
                .size(monitor.getSize())
                .year(monitor.getYear())
                .serv(monitor.getServ())
                .city(monitor.getCity().getId())
                .location(monitor.getLocation().getId())
                .room(monitor.getRoom())
                .userId(monitor.getUser().getId())
                .staydate(monitor.getStaydate())
                .price(monitor.getPrice())
                .comment(monitor.getComment())
                .build();

    }
    private Monitor mapToEntity(MonitorDTO monitorDTO) {
        Monitor monitor = new Monitor();

        monitor.setI_card(monitorDTO.getI_card());
        monitor.setI_card(monitorDTO.getI_card());
        monitor.setSerialnumber(monitorDTO.getSerialnumber());
        monitor.setI_number(monitorDTO.getI_number());
        monitor.setModel(modelRepository.findById(monitorDTO.getModel())
                .orElseThrow(() -> new EntityNotFoundException("Model not found")));
        monitor.setItemType(itemTypeRepository.findById(monitorDTO.getItemType())
                .orElseThrow(() -> new EntityNotFoundException("ItemType not found")));
        monitor.setSize(monitorDTO.getSize());
        monitor.setYear(monitorDTO.getYear());
        monitor.setServ(monitorDTO.getServ());
        monitor.setCity(cityRepository.findById(monitorDTO.getCity())
                .orElseThrow(() -> new EntityNotFoundException("City not found")));
        monitor.setLocation(locationRepository.findById(monitorDTO.getLocation())
                .orElseThrow(() -> new EntityNotFoundException("Location not found")));
        monitor.setRoom(monitorDTO.getRoom());
        monitor.setProduction(productionRepository.findById(monitorDTO.getProduction())
                .orElseThrow(() -> new EntityNotFoundException("Production not found")));
        monitor.setUser(userRepository.findById(monitorDTO.getUserId())
                        .orElseThrow(()->new EntityNotFoundException("User not found")));
        monitor.setStaydate(monitorDTO.getStaydate());
        monitor.setPrice(monitorDTO.getPrice());
        monitor.setComment(monitorDTO.getComment());
        return monitor;
    }


}
