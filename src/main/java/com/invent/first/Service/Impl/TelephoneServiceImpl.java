package com.invent.first.Service.Impl;

import com.invent.first.DTO.*;
import com.invent.first.DTO.OutDTO.TelephoneOutDTO;
import com.invent.first.Entity.Telephones;
import com.invent.first.Entity.User;
import com.invent.first.Repository.*;
import com.invent.first.Service.TelephoneService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository;
    private final UserRepository userRepository;
    private final ProductionRepository productionRepository;
    private final ModelRepository modelRepository;
    private final LocationRepository locationRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final CityRepository cityRepository;

    @Autowired
    public TelephoneServiceImpl(TelephoneRepository telephoneRepository,UserRepository userRepository,ProductionRepository productionRepository,
                                ModelRepository modelRepository,LocationRepository locationRepository, ItemTypeRepository itemTypeRepository,CityRepository cityRepository) {
        this.telephoneRepository = telephoneRepository;
        this.productionRepository = productionRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public TelephoneDTO getTelephoneById(Integer telephoneId) {
        Telephones telephone = telephoneRepository.findById(telephoneId)
                .orElseThrow(()->new EntityNotFoundException("Telephone not found"));

        return mapToDTOWithId(telephone);
    }



    @Override
    public TelephoneOutDTO getTelephoneOutById(Integer telephoneId) {
        Telephones telephone = telephoneRepository.findById(telephoneId)
                .orElseThrow(()->new EntityNotFoundException("Telephone not found"));

        return mapToDTO(telephone);

    }


    @Override
    public Telephones updateTelephone(Integer telephoneId, TelephoneDTO telephoneDTO) {
        Telephones telephone = telephoneRepository.findById(telephoneId)
                .orElseThrow(()-> new EntityNotFoundException("Telephone not found"));
        telephone.setId(telephoneId);
        telephone.setI_card(telephoneDTO.getI_card());
        telephone.setSerialnumber(telephoneDTO.getSerialnumber());
        telephone.setI_number(telephoneDTO.getI_number());
        telephone.setProduction(productionRepository.findById(telephoneDTO.getProduction())
                .orElseThrow(()->new EntityNotFoundException("Production not found")));
        telephone.setModel(modelRepository.findById(telephoneDTO.getModel())
                .orElseThrow(()->new EntityNotFoundException("Model not Found")));
        telephone.setItemType(itemTypeRepository.findById(telephoneDTO.getItemType())
                .orElseThrow(()-> new EntityNotFoundException("Item type not Found")));
        telephone.setMac(telephoneDTO.getMac());
        telephone.setYear(telephoneDTO.getYear());
        telephone.setServ(telephoneDTO.getServ());
        telephone.setCity(cityRepository.findById(telephoneDTO.getCity())
                .orElseThrow(()-> new EntityNotFoundException("City not Found")));
        telephone.setLocation(locationRepository.findById(telephoneDTO.getLocation())
                .orElseThrow(()-> new EntityNotFoundException("Location not Found")));
        telephone.setRoom(telephoneDTO.getRoom());
        telephone.setUser(userRepository.findById(telephoneDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not Found")));
        telephone.setStaydate(telephoneDTO.getStaydate());
        telephone.setPrice(telephoneDTO.getPrice());
        telephone.setComment(telephoneDTO.getComment());
        telephone.setSpisano(telephoneDTO.getSpisano());
        return telephoneRepository.save(telephone);
    }

    @Override
    public TelephoneDTO addTelephone(TelephoneDTO telephoneDTO) {
        Telephones telephone = mapToEntity(telephoneDTO);
        Telephones savedTelephone = telephoneRepository.save(telephone);
        return mapToDTOWithId(savedTelephone);
    }



    @Override
    public List<TelephoneOutDTO> getAllTelephonesWithDetails() {
        List<Telephones> telephones = telephoneRepository.findAllTelephonesWithDetails();
        return mapToDTOs(telephones);
    }

    @Override
    public List<TelephoneOutDTO> getByDept(Integer deptId) {
        List<Telephones> telephones = telephoneRepository.findByDept(deptId);
        return mapToDTOs(telephones);
    }


    @Override
    public Telephones deleteById(Integer telephoneId) {
        Telephones telephone = telephoneRepository.findById(telephoneId)
                .orElseThrow(()-> new EntityNotFoundException("Telephone not Found"));
        telephoneRepository.deleteById(telephoneId);
        return telephone;
    }
    private TelephoneDTO mapToDTOWithId(Telephones telephone) {
        return TelephoneDTO
                .builder()
                .id(telephone.getId())
                .i_card(telephone.getI_card())
                .serialnumber(telephone.getSerialnumber())
                .i_number(telephone.getI_number())
                .production(telephone.getProduction().getId())
                .model(telephone.getModel().getId())
                .itemType(telephone.getItemType().getId())
                .mac(telephone.getMac())
                .year(telephone.getYear())
                .serv(telephone.getServ())
                .city(telephone.getCity().getId())
                .location(telephone.getLocation().getId())
                .room(telephone.getRoom())
                .userId(telephone.getUser().getId())
                .staydate(telephone.getStaydate())
                .price(telephone.getPrice())
                .comment(telephone.getComment())
                .spisano(telephone.getSpisano())
                .build();
    }
    private TelephoneOutDTO mapToDTO(Telephones telephone) {
        UserDTO userDTO = UserDTO.fromUser(telephone.getUser());
        ProductionsDTO productionsDTO = ProductionsDTO.fromProductions(telephone.getProduction());
        ModelDTO modelDTO = ModelDTO.fromModel(telephone.getModel());
        ItemTypeDTO itemTypeDTO = ItemTypeDTO.fromItemType(telephone.getItemType());
        CityDTO cityDTO = CityDTO.fromCity(telephone.getCity());
        LocationDTO locationDTO = LocationDTO.fromLocation(telephone.getLocation());
        return TelephoneOutDTO
                .builder()
                .id(telephone.getId())
                .i_card(telephone.getI_card())
                .serialnumber(telephone.getSerialnumber())
                .i_number(telephone.getI_number())
                .production(productionsDTO)
                .model(modelDTO)
                .itemType(itemTypeDTO)
                .mac(telephone.getMac())
                .year(telephone.getYear())
                .serv(telephone.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(telephone.getRoom())
                .userId(userDTO)
                .staydate(telephone.getStaydate())
                .price(telephone.getPrice())
                .comment(telephone.getComment())
                .spisano(telephone.getSpisano())
                .build();

    }
    private Telephones mapToEntity(TelephoneDTO telephoneDTO) {
        Telephones telephone = new Telephones();
        telephone.setI_card(telephoneDTO.getI_card());
        telephone.setSerialnumber(telephoneDTO.getSerialnumber());
        telephone.setI_number(telephoneDTO.getI_number());
        telephone.setProduction(productionRepository.findById(telephoneDTO.getProduction())
                .orElseThrow(()->new EntityNotFoundException("Production not found")));
        telephone.setModel(modelRepository.findById(telephoneDTO.getModel())
                .orElseThrow(()->new EntityNotFoundException("Model not Found")));
        telephone.setItemType(itemTypeRepository.findById(telephoneDTO.getItemType())
                .orElseThrow(()-> new EntityNotFoundException("Item type not Found")));
        telephone.setMac(telephoneDTO.getMac());
        telephone.setYear(telephoneDTO.getYear());
        telephone.setServ(telephoneDTO.getServ());
        telephone.setCity(cityRepository.findById(telephoneDTO.getCity())
                .orElseThrow(()-> new EntityNotFoundException("City not Found")));
        telephone.setLocation(locationRepository.findById(telephoneDTO.getLocation())
                .orElseThrow(()-> new EntityNotFoundException("Location not Found")));
        telephone.setRoom(telephoneDTO.getRoom());
        telephone.setUser(userRepository.findById(telephoneDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not Found")));
        telephone.setStaydate(telephoneDTO.getStaydate());
        telephone.setPrice(telephoneDTO.getPrice());
        telephone.setComment(telephoneDTO.getComment());
        telephone.setSpisano(telephoneDTO.getSpisano());
        return telephone;
    }
    private List<TelephoneOutDTO> mapToDTOs(List<Telephones> telephones) {
        return telephones.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
