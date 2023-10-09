package com.invent.first.Service.Impl;


import com.invent.first.DTO.*;
import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.Entity.Monitor;
import com.invent.first.Repository.*;
import com.invent.first.Service.MonitorService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    private EntityManager entityManager;
    @Autowired
    public MonitorServiceImpl(MonitorRepository monitorRepository, UserRepository userRepository, ProductionRepository productionRepository,
                              ModelRepository modelRepository, LocationRepository locationRepository, ItemTypeRepository itemTypeRepository,
                              CityRepository cityRepository,EntityManager entityManager){

        this.monitorRepository = monitorRepository;
        this.productionRepository = productionRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.cityRepository = cityRepository;
        this.entityManager = entityManager;

    }

    //добавление компьютера только с ID в JSON объекте (вывод JSON объекта только с Id присоединенных столбцов )
    @Override
    public MonitorDTO addMonitor(MonitorDTO monitorDTO) {
        Monitor monitor = mapToEntity(monitorDTO);
        Monitor savedMonitor = monitorRepository.save(monitor);
        return mapToDTOWithId(savedMonitor);
    }

    @Override
    public MonitorDTO getMonitorById(Integer monitorId) {
        Monitor monitor = monitorRepository.findById(monitorId)
                .orElseThrow(()-> new EntityNotFoundException("Monitor not found"));
        return mapToDTOWithId(monitor);
    }

    @Override
    public MonitorOutDTO getMonitorOutById(Integer monitorId) {
        Monitor monitor = monitorRepository.findById(monitorId)
                .orElseThrow(()-> new EntityNotFoundException("Monitor not found"));
        return mapToDTO(monitor);
    }


    //вывод JSON объекта с полноценными объектами вместо Id
    @Override
    public List<MonitorOutDTO> getAllMonitorsWithDetails() {
        List<Monitor> monitors = monitorRepository.findAllMonitorsWithDetails();
        return mapToDTOs(monitors);
    }
    public List<MonitorOutDTO> getAllMonitorPairs(){
        entityManager.clear();
        List<Integer> monitorIds = monitorRepository.findPairsToComputer();
        // Проверка, что есть хотя бы один monitor.id
        if (monitorIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<Monitor> monitors = monitorRepository.findAllById(monitorIds);

        return mapToDTOs(monitors);
    }

    @Override
    public Monitor updateMonitor(Integer monitorId, MonitorDTO monitorDTO) {
        Monitor monitor = monitorRepository.findById(monitorId)
                .orElseThrow(()-> new EntityNotFoundException("Monitor not found"));
        monitor.setId(monitor.getId());
        monitor.setI_card(monitorDTO.getI_card());
        monitor.setSerialnumber(monitorDTO.getSerialnumber());
        monitor.setI_number(monitorDTO.getI_number());
        monitor.setModel(modelRepository.findById(monitorDTO.getModel())
                .orElseThrow(() -> new EntityNotFoundException("Model not found")));
        monitor.setItemType(itemTypeRepository.findById(monitorDTO.getItemType())
                .orElseThrow(() -> new EntityNotFoundException("ItemType not found")));
        monitor.setSize(monitorDTO.getSize());
        monitor.setHdmi(monitorDTO.getHdmi());
        monitor.setVga(monitorDTO.getVga());
        monitor.setDisplayport(monitorDTO.getDisplayport());
        monitor.setDvid(monitorDTO.getDvid());
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
        monitor.setSpisano(monitorDTO.getSpisano());
        return monitorRepository.save(monitor);
    }

    @Override
    public Monitor deleteById(Integer monitorId) {
        Monitor monitor = monitorRepository.findById(monitorId)
                .orElseThrow(()-> new EntityNotFoundException("Monitor not found"));
        monitorRepository.deleteById(monitorId);
        return monitor;
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
                .hdmi(monitor.getHdmi())
                .vga(monitor.getVga())
                .displayport(monitor.getDisplayport())
                .dvid(monitor.getDvid())
                .year(monitor.getYear())
                .serv(monitor.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(monitor.getRoom())
                .userId(userDTO)
                .staydate(monitor.getStaydate())
                .price(monitor.getPrice())
                .comment(monitor.getComment())
                .spisano(monitor.getSpisano())
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
                .hdmi(monitor.getHdmi())
                .vga(monitor.getVga())
                .displayport(monitor.getDisplayport())
                .dvid(monitor.getDvid())
                .year(monitor.getYear())
                .serv(monitor.getServ())
                .city(monitor.getCity().getId())
                .location(monitor.getLocation().getId())
                .room(monitor.getRoom())
                .userId(monitor.getUser().getId())
                .staydate(monitor.getStaydate())
                .price(monitor.getPrice())
                .comment(monitor.getComment())
                .spisano(monitor.getSpisano())
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
        monitor.setHdmi(monitorDTO.getHdmi());
        monitor.setVga(monitorDTO.getVga());
        monitor.setDisplayport(monitorDTO.getDisplayport());
        monitor.setDvid(monitorDTO.getDvid());
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
        monitor.setSpisano(monitorDTO.getSpisano());
        return monitor;
    }


}
