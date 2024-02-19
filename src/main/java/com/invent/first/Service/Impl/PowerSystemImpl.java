package com.invent.first.Service.Impl;

import com.invent.first.DTO.*;
import com.invent.first.DTO.OutDTO.PowerSystemOutDTO;
import com.invent.first.Entity.PowerSystem;
import com.invent.first.Repository.*;
import com.invent.first.Service.PowerSystemService;
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
public class PowerSystemImpl implements PowerSystemService {
    private final PowerSysRepository powerSysRepository;
    private final UserRepository userRepository;
    private final ProductionRepository productionRepository;
    private final ModelRepository modelRepository;
    private final LocationRepository locationRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final CityRepository cityRepository;
    @Autowired
    public PowerSystemImpl(PowerSysRepository powerSysRepository, UserRepository userRepository, ProductionRepository productionRepository,
                           ModelRepository modelRepository, LocationRepository locationRepository, ItemTypeRepository itemTypeRepository,
                           CityRepository cityRepository) {
        this.powerSysRepository = powerSysRepository;
        this.userRepository = userRepository;
        this.productionRepository = productionRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public PowerSystemDTO getPSById(Integer psId) {
        PowerSystem powerSystem = powerSysRepository.findById(psId)
                .orElseThrow(()-> new EntityNotFoundException("Server or commutator not found"));
        return mapToDTOWithId(powerSystem);
    }

    @Override
    public PowerSystemOutDTO getPSOutById(Integer psId) {
        PowerSystem powerSystem = powerSysRepository.findById(psId)
                .orElseThrow(()-> new EntityNotFoundException("Server or commutator not found"));
        return mapToDTO(powerSystem);
    }

    @Override
    public PowerSystem updatePS(Integer psId, PowerSystemDTO powerSystemDTO) {
        PowerSystem powerSystem = powerSysRepository.findById(psId)
                .orElseThrow(()-> new EntityNotFoundException("Power System not found"));
        powerSystem.setId(psId);
        powerSystem.setI_card(powerSystemDTO.getI_card());
        powerSystem.setSerialnumber(powerSystemDTO.getSerialnumber());
        powerSystem.setI_number(powerSystemDTO.getI_number());
        powerSystem.setProduction(productionRepository.findById(powerSystemDTO.getProduction())
                .orElseThrow(()->new EntityNotFoundException("Production not found")));
        powerSystem.setModel(modelRepository.findById(powerSystemDTO.getModel())
                .orElseThrow(()->new EntityNotFoundException("Model not Found")));
        powerSystem.setItemType(itemTypeRepository.findById(powerSystemDTO.getItemType())
                .orElseThrow(()-> new EntityNotFoundException("Item type not Found")));
        powerSystem.setIp(powerSystemDTO.getIp());
        powerSystem.setNetwork(powerSystemDTO.getNetwork());
        powerSystem.setYear(powerSystemDTO.getYear());
        powerSystem.setServ(powerSystemDTO.getServ());
        powerSystem.setCity(cityRepository.findById(powerSystemDTO.getCity())
                .orElseThrow(()-> new EntityNotFoundException("City not Found")));
        powerSystem.setLocation(locationRepository.findById(powerSystemDTO.getLocation())
                .orElseThrow(()-> new EntityNotFoundException("Location not Found")));
        powerSystem.setRoom(powerSystemDTO.getRoom());
        powerSystem.setUser(userRepository.findById(powerSystemDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not Found")));
        powerSystem.setStaydate(powerSystemDTO.getStaydate());
        powerSystem.setPrice(powerSystemDTO.getPrice());
        powerSystem.setComment(powerSystemDTO.getComment());
        powerSystem.setSpisano(powerSystemDTO.getSpisano());
        return powerSysRepository.save(powerSystem);
    }

    @Override
    public PowerSystemDTO addPowerSystem(PowerSystemDTO powerSystemDTO) {
        PowerSystem powerSystem = mapToEntity(powerSystemDTO);
        PowerSystem savedPS = powerSysRepository.save(powerSystem);
        return mapToDTOWithId(savedPS);
    }

    @Override
    public List<PowerSystemOutDTO> getByDept(Integer deptId) {
        List<PowerSystem> powerSystems = powerSysRepository.findByDept(deptId);
        return mapToDTOs(powerSystems);
    }

    @Override
    public List<PowerSystemOutDTO> getAllPSWithDetails() {
        List<PowerSystem> powerSystems = powerSysRepository.findAllWithDetails();
        return mapToDTOs(powerSystems);
    }

    @Override
    @Transactional
    public PowerSystem deleteById(Integer psId) {
        PowerSystem powerSystem = powerSysRepository.findById(psId)
                .orElseThrow(()-> new EntityNotFoundException("Power System not found"));
        powerSysRepository.deleteById(psId);
        return powerSystem;
    }

    private PowerSystemDTO mapToDTOWithId(PowerSystem powerSystem) {
        return PowerSystemDTO
                .builder()
                .id(powerSystem.getId())
                .i_card(powerSystem.getI_card())
                .serialnumber(powerSystem.getSerialnumber())
                .i_number(powerSystem.getI_number())
                .production(powerSystem.getProduction().getId())
                .model(powerSystem.getModel().getId())
                .itemType(powerSystem.getItemType().getId())
                .ip(powerSystem.getIp())
                .network(powerSystem.getNetwork())
                .year(powerSystem.getYear())
                .serv(powerSystem.getServ())
                .city(powerSystem.getCity().getId())
                .location(powerSystem.getLocation().getId())
                .room(powerSystem.getRoom())
                .userId(powerSystem.getUser().getId())
                .staydate(powerSystem.getStaydate())
                .price(powerSystem.getPrice())
                .comment(powerSystem.getComment())
                .spisano(powerSystem.getSpisano())
                .build();
    }

    private PowerSystemOutDTO mapToDTO(PowerSystem powerSystem) {
        UserDTO userDTO = UserDTO.fromUser(powerSystem.getUser());
        ProductionsDTO productionsDTO = ProductionsDTO.fromProductions(powerSystem.getProduction());
        ModelDTO modelDTO = ModelDTO.fromModel(powerSystem.getModel());
        ItemTypeDTO itemTypeDTO = ItemTypeDTO.fromItemType(powerSystem.getItemType());
        CityDTO cityDTO = CityDTO.fromCity(powerSystem.getCity());
        LocationDTO locationDTO = LocationDTO.fromLocation(powerSystem.getLocation());
        return PowerSystemOutDTO
                .builder()
                .id(powerSystem.getId())
                .i_card(powerSystem.getI_card())
                .serialnumber(powerSystem.getSerialnumber())
                .i_number(powerSystem.getI_number())
                .production(productionsDTO)
                .model(modelDTO)
                .itemType(itemTypeDTO)
                .ip(powerSystem.getIp())
                .network(powerSystem.getNetwork())
                .year(powerSystem.getYear())
                .serv(powerSystem.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(powerSystem.getRoom())
                .userId(userDTO)
                .staydate(powerSystem.getStaydate())
                .price(powerSystem.getPrice())
                .comment(powerSystem.getComment())
                .spisano(powerSystem.getSpisano())
                .build();
    }

    private PowerSystem mapToEntity(PowerSystemDTO powerSystemDTO) {
        PowerSystem powerSystem = new PowerSystem();
        powerSystem.setI_card(powerSystemDTO.getI_card());
        powerSystem.setSerialnumber(powerSystemDTO.getSerialnumber());
        powerSystem.setI_number(powerSystemDTO.getI_number());
        powerSystem.setProduction(productionRepository.findById(powerSystemDTO.getProduction())
                .orElseThrow(()->new EntityNotFoundException("Production not found")));
        powerSystem.setModel(modelRepository.findById(powerSystemDTO.getModel())
                .orElseThrow(()->new EntityNotFoundException("Model not Found")));
        powerSystem.setItemType(itemTypeRepository.findById(powerSystemDTO.getItemType())
                .orElseThrow(()-> new EntityNotFoundException("Item type not Found")));
        powerSystem.setIp(powerSystemDTO.getIp());
        powerSystem.setNetwork(powerSystemDTO.getNetwork());
        powerSystem.setYear(powerSystemDTO.getYear());
        powerSystem.setServ(powerSystemDTO.getServ());
        powerSystem.setCity(cityRepository.findById(powerSystemDTO.getCity())
                .orElseThrow(()-> new EntityNotFoundException("City not Found")));
        powerSystem.setLocation(locationRepository.findById(powerSystemDTO.getLocation())
                .orElseThrow(()-> new EntityNotFoundException("Location not Found")));
        powerSystem.setRoom(powerSystemDTO.getRoom());
        powerSystem.setUser(userRepository.findById(powerSystemDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not Found")));
        powerSystem.setStaydate(powerSystemDTO.getStaydate());
        powerSystem.setPrice(powerSystemDTO.getPrice());
        powerSystem.setComment(powerSystemDTO.getComment());
        powerSystem.setSpisano(powerSystemDTO.getSpisano());
        return powerSystem;
    }
    private List<PowerSystemOutDTO> mapToDTOs(List<PowerSystem> powerSystems){
        return powerSystems.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

}
