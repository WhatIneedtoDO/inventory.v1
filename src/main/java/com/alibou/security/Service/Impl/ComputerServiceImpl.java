package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.*;
import com.alibou.security.DTO.OutDTO.ComputerOutDTO;
import com.alibou.security.Entity.Computer;
import com.alibou.security.Entity.User;
import com.alibou.security.Repository.*;
import com.alibou.security.Service.ComputerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;



@Service
@PersistenceContext
@Transactional
public class ComputerServiceImpl implements ComputerService {
    private  ComputerRepository computerRepository;
    private UserRepository userRepository;
    private ProductionRepository productionRepository;
    private ModelRepository modelRepository;
    private LocationRepository locationRepository;
    private ItemTypeRepository itemTypeRepository;
    private CityRepository cityRepository;
    private MotherBProdRepos motherBProdRepos;
    private MotherBModelRepos motherBModelRepos;
    private CpuProdRepository cpuProdRepository;
    private CpuModelRepository cpuModelRepository;

    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository, UserRepository userRepository, ProductionRepository productionRepository,
                               ModelRepository modelRepository, LocationRepository locationRepository, ItemTypeRepository itemTypeRepository, CityRepository cityRepository,
                               MotherBProdRepos motherBProdRepos,MotherBModelRepos motherBModelRepos, CpuProdRepository cpuProdRepository,
                               CpuModelRepository cpuModelRepository) {

        this.productionRepository = productionRepository;
        this.computerRepository = computerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.cityRepository = cityRepository;
        this.motherBModelRepos = motherBModelRepos;
        this.motherBProdRepos = motherBProdRepos;
        this.cpuProdRepository = cpuProdRepository;
        this.cpuModelRepository = cpuModelRepository;
    }

    @Override
    public ComputerDTO getComputerById(Integer computerId) {
        Computer computer = computerRepository.findById(computerId)
                .orElseThrow(() -> new EntityNotFoundException("Computer not found"));
        return mapToDTOWithId(computer);
    }

    @Override
    public ComputerOutDTO getComputerOutById(Integer computerId) {
        Computer computer = computerRepository.findById(computerId)
                .orElseThrow(() -> new EntityNotFoundException("Computer not found"));
        return mapToDTO(computer);
    }

    @Override
    public Computer updateComputer(Integer computerId, ComputerDTO computerDTO) {
        Computer computer = computerRepository.findById(computerId)
                .orElseThrow(() -> new EntityNotFoundException("Computer not found"));
        Integer userId = computerDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        computer.setId(computerId);
        computer.setI_card(computerDTO.getI_card());
        computer.setUser(user);
        computer.setProduction(productionRepository.findById(computerDTO.getProduction())
                .orElseThrow(()-> new EntityNotFoundException("Production not found")));
        computer.setSerialnumber(computerDTO.getSerialnumber());
        computer.setI_number(computerDTO.getI_number());
        computer.setModel(modelRepository.findById(computerDTO.getModel())
                .orElseThrow(() -> new EntityNotFoundException("Model not found")));
        computer.setItemType(itemTypeRepository.findById(computerDTO.getItemType())
                .orElseThrow(() -> new EntityNotFoundException("Item type not found")));
        computer.setSsd(computerDTO.getSsd());
        computer.setHdd(computerDTO.getHdd());
        computer.setMotherbprod(motherBProdRepos.findById(computerDTO.getMotherBProd())
                .orElseThrow(()-> new EntityNotFoundException("MotherBoard not found")));
        computer.setMotherbmodel(motherBModelRepos.findById(computerDTO.getMotherBModel())
                .orElseThrow(()->new EntityNotFoundException("Motherboard model not found")));
        computer.setSlotsvalue(computerDTO.getSlotsvalue());
        computer.setSlotsuse(computerDTO.getSlotsuse());
        computer.setRamtype(computerDTO.getRamtype());
        computer.setRam(computerDTO.getRam());
        computer.setBp(computerDTO.getBp());
        computer.setCpuproduction(cpuProdRepository.findById(computerDTO.getProduction())
                .orElseThrow(()-> new EntityNotFoundException("Cpu Production not found")));
        computer.setCpumodel(cpuModelRepository.findById(computerDTO.getCpumodel())
                .orElseThrow(()-> new EntityNotFoundException("Cpu Model not Found")));
        computer.setYear(computerDTO.getYear());
        computer.setServ(computerDTO.getServ());
        computer.setCity(cityRepository.findById(computerDTO.getCity())
                .orElseThrow(() -> new EntityNotFoundException("City not found")));
        computer.setLocation(locationRepository.findById(computerDTO.getLocation())
                .orElseThrow(() -> new EntityNotFoundException("Location not found")));
        computer.setRoom(computerDTO.getRoom());
        computer.setStaydate(computerDTO.getStaydate());
        computer.setPrice(computerDTO.getPrice());
        computer.setComment(computerDTO.getComment());
        return computerRepository.save(computer);
    }

    //добавление компьютера только с ID в JSON объекте (вывод JSON объекта только с Id присоединенных столбцов )
    @Override
    public ComputerDTO addComputer(ComputerDTO computerDTO) {
        Computer computer = mapToEntity(computerDTO);

        Computer savedComputer = computerRepository.save(computer);
        return mapToDTOWithId(savedComputer);
    }



    //вывод JSON объекта с полноценными объектами вместо Id
    @Override
    public List<ComputerOutDTO> getAllComputersWithDetails() {
        List<Computer> computers = computerRepository.findAllComputersWithDetails();
        return mapToDTOs(computers);
    }


    //используется ComputerDTO чтобы в базу сохранялись только ID
    private Computer mapToEntity(ComputerDTO computerDTO) {
        Integer userId = computerDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Computer computer = new Computer();

        computer.setI_card(computerDTO.getI_card());
        computer.setUser(user);
        computer.setProduction(productionRepository.findById(computerDTO.getProduction())
                .orElseThrow(()-> new EntityNotFoundException("Production not found")));
        computer.setSerialnumber(computerDTO.getSerialnumber());
        computer.setI_number(computerDTO.getI_number());
        computer.setModel(modelRepository.findById(computerDTO.getModel())
                .orElseThrow(() -> new EntityNotFoundException("Model not found")));
        computer.setItemType(itemTypeRepository.findById(computerDTO.getItemType())
                .orElseThrow(() -> new EntityNotFoundException("Item type not found")));
        computer.setSsd(computerDTO.getSsd());
        computer.setHdd(computerDTO.getHdd());
        computer.setMotherbprod(motherBProdRepos.findById(computerDTO.getMotherBProd())
                .orElseThrow(()-> new EntityNotFoundException("MotherBoard not found")));
        computer.setMotherbmodel(motherBModelRepos.findById(computerDTO.getMotherBModel())
                .orElseThrow(()->new EntityNotFoundException("Motherboard model not found")));
        computer.setSlotsvalue(computerDTO.getSlotsvalue());
        computer.setSlotsuse(computerDTO.getSlotsuse());
        computer.setRamtype(computerDTO.getRamtype());
        computer.setRam(computerDTO.getRam());
        computer.setBp(computerDTO.getBp());
        computer.setCpuproduction(cpuProdRepository.findById(computerDTO.getProduction())
                .orElseThrow(()-> new EntityNotFoundException("Cpu Production not found")));
        computer.setCpumodel(cpuModelRepository.findById(computerDTO.getCpumodel())
                .orElseThrow(()-> new EntityNotFoundException("Cpu Model not Found")));
        computer.setYear(computerDTO.getYear());
        computer.setServ(computerDTO.getServ());
        computer.setCity(cityRepository.findById(computerDTO.getCity())
                .orElseThrow(() -> new EntityNotFoundException("City not found")));
        computer.setLocation(locationRepository.findById(computerDTO.getLocation())
                .orElseThrow(() -> new EntityNotFoundException("Location not found")));
        computer.setRoom(computerDTO.getRoom());
        computer.setStaydate(computerDTO.getStaydate());
        computer.setPrice(computerDTO.getPrice());
        computer.setComment(computerDTO.getComment());
        return computer;
    }



    //mapToDTO
    private List<ComputerOutDTO> mapToDTOs(List<Computer> computers) {
        return computers.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
//ComputerOutDTO для вытаскивания из базы полноценных JSON объектов ,а не только ID
    private ComputerOutDTO mapToDTO(Computer computer) {
        UserDTO userDTO = UserDTO.fromUser(computer.getUser());
        ProductionsDTO productionsDTO = ProductionsDTO.fromProductions(computer.getProduction());
        ModelDTO modelDTO = ModelDTO.fromModel(computer.getModel());
        ItemTypeDTO itemTypeDTO = ItemTypeDTO.fromItemType(computer.getItemType());
        CityDTO cityDTO = CityDTO.fromCity(computer.getCity());
        LocationDTO locationDTO = LocationDTO.fromLocation(computer.getLocation());
        MotherBModelDTO motherBModelDTO = MotherBModelDTO.fromMotherBModel(computer.getMotherbmodel());
        MotherBProdDTO motherBProdDTO = MotherBProdDTO.fromMotherBProd(computer.getMotherbprod());
        CpuModelDTO cpuModelDTO = CpuModelDTO.fromCpuModel(computer.getCpumodel());
        CpuProductionDTO cpuProductionDTO = CpuProductionDTO.fromCpuProduction(computer.getCpuproduction());
        return ComputerOutDTO
                .builder()
                .id(computer.getId())
                .i_card(computer.getI_card())
                .serialnumber(computer.getSerialnumber())
                .i_number(computer.getI_number())
                .production(productionsDTO)
                .model(modelDTO)
                .itemType(itemTypeDTO)
                .ssd(computer.getSsd().getValue())
                .hdd(computer.getHdd().getValue())
                .motherBProd(motherBProdDTO)
                .motherBModel(motherBModelDTO)
                .slotsvalue(computer.getSlotsvalue())
                .slotsuse(computer.getSlotsuse())
                .ramtype(computer.getRamtype())
                .ram(computer.getRam().getValue())
                .bp(computer.getBp())
                .cpuproduction(cpuProductionDTO)
                .cpumodel(cpuModelDTO)
                .year(computer.getYear())
                .serv(computer.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(computer.getRoom())
                .userId(userDTO)
                .staydate(computer.getStaydate())
                .price(computer.getPrice())
                .comment(computer.getComment())
                .build();

    }
    private ComputerDTO mapToDTOWithId(Computer computer) {

        return ComputerDTO
                .builder()
                .id(computer.getId())
                .i_card(computer.getI_card())
                .serialnumber(computer.getSerialnumber())
                .i_number(computer.getI_number())
                .production(computer.getProduction().getId())
                .model(computer.getModel().getId())
                .itemType(computer.getItemType().getId())
                .ssd(computer.getSsd())
                .hdd(computer.getHdd())
                .motherBProd(computer.getMotherbprod().getId())
                .motherBModel(computer.getMotherbmodel().getId())
                .slotsvalue(computer.getSlotsvalue())
                .slotsuse(computer.getSlotsuse())
                .ramtype(computer.getRamtype())
                .ram(computer.getRam())
                .bp(computer.getBp())
                .cpuproduction(computer.getCpuproduction().getId())
                .cpumodel(computer.getCpumodel().getId())
                .year(computer.getYear())
                .serv(computer.getServ())
                .city(computer.getCity().getId())
                .location(computer.getLocation().getId())
                .room(computer.getRoom())
                .userId(computer.getUser().getId())
                .staydate(computer.getStaydate())
                .price(computer.getPrice())
                .comment(computer.getComment())
                .build();

    }


}
