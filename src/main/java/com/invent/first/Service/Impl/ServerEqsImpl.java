package com.invent.first.Service.Impl;

import com.invent.first.DTO.*;
import com.invent.first.DTO.OutDTO.ServerEqsOutDTO;
import com.invent.first.Entity.ServerEqs;
import com.invent.first.Entity.Trash;
import com.invent.first.Repository.*;
import com.invent.first.Service.ServerEqsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class ServerEqsImpl implements ServerEqsService {

    private final ServerEqsRepository serverEqsRepository;
    private final UserRepository userRepository;
    private final ProductionRepository productionRepository;
    private final ModelRepository modelRepository;
    private final LocationRepository locationRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final CityRepository cityRepository;


    @Autowired
    public ServerEqsImpl (ServerEqsRepository serverEqsRepository, UserRepository userRepository, ProductionRepository productionRepository,
                          ModelRepository modelRepository, LocationRepository locationRepository, ItemTypeRepository itemTypeRepository,
                          CityRepository cityRepository){
        this.serverEqsRepository = serverEqsRepository;
        this.userRepository = userRepository;
        this.productionRepository = productionRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public ServerEqsDTO getServerEqsById(Integer eqsId) {
        ServerEqs serverEqs = serverEqsRepository.findById(eqsId)
                .orElseThrow(()-> new EntityNotFoundException("Server or commutator not found"));
        return mapToDTOWithId(serverEqs);
    }

    @Override
    public ServerEqsOutDTO getServerEqsOutById(Integer eqsId) {
        ServerEqs serverEqs = serverEqsRepository.findById(eqsId)
                .orElseThrow(()-> new EntityNotFoundException("Server or commutator not found"));
        return mapToDTO(serverEqs);
    }

    @Override
    public ServerEqs updateServerEqs(Integer eqsId, ServerEqsDTO serverEqsDTO) {
        ServerEqs serverEqs = serverEqsRepository.findById(eqsId)
                .orElseThrow(()-> new EntityNotFoundException("Telephone not found"));
        serverEqs.setId(eqsId);
        serverEqs.setI_card(serverEqsDTO.getI_card());
        serverEqs.setSerialnumber(serverEqsDTO.getSerialnumber());
        serverEqs.setI_number(serverEqsDTO.getI_number());
        serverEqs.setProduction(productionRepository.findById(serverEqsDTO.getProduction())
                .orElseThrow(()->new EntityNotFoundException("Production not found")));
        serverEqs.setModel(modelRepository.findById(serverEqsDTO.getModel())
                .orElseThrow(()->new EntityNotFoundException("Model not Found")));
        serverEqs.setItemType(itemTypeRepository.findById(serverEqsDTO.getItemType())
                .orElseThrow(()-> new EntityNotFoundException("Item type not Found")));
        serverEqs.setName(serverEqsDTO.getName());
        serverEqs.setIp(serverEqsDTO.getIp());
        serverEqs.setYear(serverEqsDTO.getYear());
        serverEqs.setServ(serverEqsDTO.getServ());
        serverEqs.setCity(cityRepository.findById(serverEqsDTO.getCity())
                .orElseThrow(()-> new EntityNotFoundException("City not Found")));
        serverEqs.setLocation(locationRepository.findById(serverEqsDTO.getLocation())
                .orElseThrow(()-> new EntityNotFoundException("Location not Found")));
        serverEqs.setRoom(serverEqsDTO.getRoom());
        serverEqs.setCloset(serverEqsDTO.getCloset());
        serverEqs.setUser(userRepository.findById(serverEqsDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not Found")));
        serverEqs.setStaydate(serverEqsDTO.getStaydate());
        serverEqs.setPrice(serverEqsDTO.getPrice());
        serverEqs.setComment(serverEqsDTO.getComment());
        serverEqs.setSpisano(serverEqsDTO.getSpisano());
        return serverEqsRepository.save(serverEqs);
    }

    @Override
    public List<ServerEqsOutDTO> getByDept(Integer deptId) {
        List<ServerEqs> serverEqs = serverEqsRepository.findByDept(deptId);
        return mapToDTOs(serverEqs);
    }

    @Override
    public ServerEqsDTO addServerEqs(ServerEqsDTO serverEqsDTO) {
        ServerEqs serverEqs = mapToEntity(serverEqsDTO);
        ServerEqs savedServerEqs = serverEqsRepository.save(serverEqs);
        return mapToDTOWithId(savedServerEqs);
    }

    @Override
    public List<ServerEqsOutDTO> getAllServerEqsWithDetails() {
        List<ServerEqs> serverEqs = serverEqsRepository.findAllServerEqsWithDetails();
        return mapToDTOs(serverEqs);
    }


    @Override
    @Transactional
    public ServerEqs deleteById(Integer eqsId) {
        ServerEqs serverEqs = serverEqsRepository.findById(eqsId)
                .orElseThrow(() -> new EntityNotFoundException("Server or Commutator not Found"));
        serverEqsRepository.deleteById(eqsId);
        return serverEqs;
    }
    private ServerEqsDTO mapToDTOWithId(ServerEqs serverEqs) {
        return ServerEqsDTO
                .builder()
                .id(serverEqs.getId())
                .i_card(serverEqs.getI_card())
                .serialnumber(serverEqs.getSerialnumber())
                .i_number(serverEqs.getI_number())
                .production(serverEqs.getProduction().getId())
                .model(serverEqs.getModel().getId())
                .itemType(serverEqs.getItemType().getId())
                .name(serverEqs.getName())
                .ip(serverEqs.getIp())
                .year(serverEqs.getYear())
                .serv(serverEqs.getServ())
                .city(serverEqs.getCity().getId())
                .location(serverEqs.getLocation().getId())
                .room(serverEqs.getRoom())
                .closet(serverEqs.getCloset())
                .userId(serverEqs.getUser().getId())
                .staydate(serverEqs.getStaydate())
                .price(serverEqs.getPrice())
                .comment(serverEqs.getComment())
                .spisano(serverEqs.getSpisano())
                .build();
    }
    private ServerEqsOutDTO mapToDTO(ServerEqs serverEqs) {
        UserDTO userDTO = UserDTO.fromUser(serverEqs.getUser());
        ProductionsDTO productionsDTO = ProductionsDTO.fromProductions(serverEqs.getProduction());
        ModelDTO modelDTO = ModelDTO.fromModel(serverEqs.getModel());
        ItemTypeDTO itemTypeDTO = ItemTypeDTO.fromItemType(serverEqs.getItemType());
        CityDTO cityDTO = CityDTO.fromCity(serverEqs.getCity());
        LocationDTO locationDTO = LocationDTO.fromLocation(serverEqs.getLocation());
        return ServerEqsOutDTO
                .builder()
                .id(serverEqs.getId())
                .i_card(serverEqs.getI_card())
                .serialnumber(serverEqs.getSerialnumber())
                .i_number(serverEqs.getI_number())
                .production(productionsDTO)
                .model(modelDTO)
                .itemType(itemTypeDTO)
                .name(serverEqs.getName())
                .ip(serverEqs.getIp())
                .year(serverEqs.getYear())
                .serv(serverEqs.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(serverEqs.getRoom())
                .closet(serverEqs.getCloset())
                .userId(userDTO)
                .staydate(serverEqs.getStaydate())
                .price(serverEqs.getPrice())
                .comment(serverEqs.getComment())
                .spisano(serverEqs.getSpisano())
                .build();
    }
    private ServerEqs mapToEntity(ServerEqsDTO serverEqsDTO) {
        ServerEqs serverEqs = new ServerEqs();
        serverEqs.setI_card(serverEqsDTO.getI_card());
        serverEqs.setSerialnumber(serverEqsDTO.getSerialnumber());
        serverEqs.setI_number(serverEqsDTO.getI_number());
        serverEqs.setProduction(productionRepository.findById(serverEqsDTO.getProduction())
                .orElseThrow(()->new EntityNotFoundException("Production not found")));
        serverEqs.setModel(modelRepository.findById(serverEqsDTO.getModel())
                .orElseThrow(()->new EntityNotFoundException("Model not Found")));
        serverEqs.setItemType(itemTypeRepository.findById(serverEqsDTO.getItemType())
                .orElseThrow(()-> new EntityNotFoundException("Item type not Found")));
        serverEqs.setName(serverEqsDTO.getName());
        serverEqs.setIp(serverEqsDTO.getIp());
        serverEqs.setYear(serverEqsDTO.getYear());
        serverEqs.setServ(serverEqsDTO.getServ());
        serverEqs.setCity(cityRepository.findById(serverEqsDTO.getCity())
                .orElseThrow(()-> new EntityNotFoundException("City not Found")));
        serverEqs.setLocation(locationRepository.findById(serverEqsDTO.getLocation())
                .orElseThrow(()-> new EntityNotFoundException("Location not Found")));
        serverEqs.setRoom(serverEqsDTO.getRoom());
        serverEqs.setCloset(serverEqsDTO.getCloset());
        serverEqs.setUser(userRepository.findById(serverEqsDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not Found")));
        serverEqs.setStaydate(serverEqsDTO.getStaydate());
        serverEqs.setPrice(serverEqsDTO.getPrice());
        serverEqs.setComment(serverEqsDTO.getComment());
        serverEqs.setSpisano(serverEqsDTO.getSpisano());
        return serverEqs;
    }
    private List<ServerEqsOutDTO> mapToDTOs(List<ServerEqs> serverEqs) {
        return serverEqs.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
