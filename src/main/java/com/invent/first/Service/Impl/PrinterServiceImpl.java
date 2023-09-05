package com.invent.first.Service.Impl;

import com.invent.first.DTO.*;
import com.invent.first.DTO.OutDTO.PrinterOutDTO;
import com.invent.first.Entity.Printers;
import com.invent.first.Repository.*;
import com.invent.first.Service.PrinterService;
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
public class PrinterServiceImpl implements PrinterService {
    private PrinterRepository printerRepository;
    private UserRepository userRepository;
    private ProductionRepository productionRepository;
    private ModelRepository modelRepository;
    private LocationRepository locationRepository;
    private ItemTypeRepository itemTypeRepository;
    private CityRepository cityRepository;


    @Autowired
    public PrinterServiceImpl(PrinterRepository printerRepository, UserRepository userRepository, ProductionRepository productionRepository,
                              ModelRepository modelRepository, LocationRepository locationRepository, ItemTypeRepository itemTypeRepository,
                              CityRepository cityRepository){
        this.printerRepository = printerRepository;
        this.userRepository = userRepository;
        this.productionRepository = productionRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
        this.itemTypeRepository = itemTypeRepository;
        this.cityRepository = cityRepository;
    }
    @Override
    public PrinterDTO getPrinterById(Integer printerId) {
        Printers printer = printerRepository.findById(printerId)
                .orElseThrow(()-> new EntityNotFoundException("Printer not Found"));
        return mapToDTOWithId(printer);
    }

    @Override
    public PrinterOutDTO getPrinterOutById(Integer printerId) {
        Printers printer = printerRepository.findById(printerId)
                .orElseThrow(()-> new EntityNotFoundException("Printer not Found"));
        return mapToDTO(printer);
    }

    @Override
    public Printers updatePrinter(Integer printerId, PrinterDTO printerDTO) {
        Printers printer = printerRepository.findById(printerId)
                .orElseThrow(()-> new EntityNotFoundException("Printer not Found"));
        printer.setId(printerId);
        printer.setI_card(printerDTO.getI_card());
        printer.setSerialnumber(printerDTO.getSerialnumber());
        printer.setI_number(printerDTO.getI_number());
        printer.setProduction(productionRepository.findById(printerDTO.getProduction())
                .orElseThrow(()-> new EntityNotFoundException("Production not Found")));
        printer.setModel(modelRepository.findById(printerDTO.getModel())
                .orElseThrow(()-> new EntityNotFoundException("Model not found")));
        printer.setItemType(itemTypeRepository.findById(printerDTO.getItemType())
                .orElseThrow(() -> new EntityNotFoundException("Item type not found")));
        printer.setName(printerDTO.getName());
        printer.setIp(printerDTO.getIp());
        printer.setType(printerDTO.getType());
        printer.setFormat(printerDTO.getFormat());
        printer.setPrinterclass(printerDTO.getPrinterClass());
        printer.setColor(printerDTO.getColor());
        printer.setTypepechat(printerDTO.getTypePechat());
        printer.setSecondSide(printerDTO.getSecondSide());
        printer.setYear(printerDTO.getYear());
        printer.setServ(printerDTO.getServ());
        printer.setCity(cityRepository.findById(printerDTO.getCity())
                .orElseThrow(() -> new EntityNotFoundException("City not found")));
        printer.setLocation(locationRepository.findById(printerDTO.getLocation())
                .orElseThrow(() -> new EntityNotFoundException("Location not found")));
        printer.setRoom(printerDTO.getRoom());
        printer.setUser(userRepository.findById(printerDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not found")));
        printer.setStaydate(printerDTO.getStaydate());
        printer.setPrice(printerDTO.getPrice());
        printer.setComment(printerDTO.getComment());

        return printerRepository.save(printer);
    }

    @Override
    public PrinterDTO addPrinter(PrinterDTO printerDTO) {
        Printers printer = mapToEntity(printerDTO);
        Printers savedPrinter = printerRepository.save(printer);
        return mapToDTOWithId(savedPrinter);
    }

    @Override
    public List<PrinterOutDTO> getAllPrintersWithDetails() {
        List<Printers> printers = printerRepository.findAllPrintersWithDetails();
        return mapToDTOs(printers);

    }

    @Override
    public Printers deleteById(Integer printerId) {
        Printers printer = printerRepository.findById(printerId)
                .orElseThrow(()-> new EntityNotFoundException("Printer not Found"));
        printerRepository.deleteById(printerId);

        return printer;
    }

    private List<PrinterOutDTO> mapToDTOs(List<Printers> printer){
        return printer.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private Printers mapToEntity(PrinterDTO printerDTO){
        Printers printer = new Printers();
        printer.setI_card(printerDTO.getI_card());
        printer.setSerialnumber(printerDTO.getSerialnumber());
        printer.setI_number(printerDTO.getI_number());
        printer.setProduction(productionRepository.findById(printerDTO.getProduction())
                .orElseThrow(()-> new EntityNotFoundException("Production not Found")));
        printer.setModel(modelRepository.findById(printerDTO.getModel())
                .orElseThrow(()-> new EntityNotFoundException("Model not found")));
        printer.setItemType(itemTypeRepository.findById(printerDTO.getItemType())
                .orElseThrow(() -> new EntityNotFoundException("Item type not found")));
        printer.setName(printerDTO.getName());
        printer.setIp(printerDTO.getIp());
        printer.setType(printerDTO.getType());
        printer.setFormat(printerDTO.getFormat());
        printer.setPrinterclass(printerDTO.getPrinterClass());
        printer.setColor(printerDTO.getColor());
        printer.setTypepechat(printerDTO.getTypePechat());
        printer.setSecondSide(printerDTO.getSecondSide());
        printer.setYear(printerDTO.getYear());
        printer.setServ(printerDTO.getServ());
        printer.setCity(cityRepository.findById(printerDTO.getCity())
                .orElseThrow(() -> new EntityNotFoundException("City not found")));
        printer.setLocation(locationRepository.findById(printerDTO.getLocation())
                .orElseThrow(() -> new EntityNotFoundException("Location not found")));
        printer.setRoom(printerDTO.getRoom());
        printer.setUser(userRepository.findById(printerDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not found")));
        printer.setStaydate(printerDTO.getStaydate());
        printer.setPrice(printerDTO.getPrice());
        printer.setComment(printerDTO.getComment());
        return printer;
    }
    private PrinterDTO mapToDTOWithId(Printers printer){
        return PrinterDTO
                .builder()
                .id(printer.getId())
                .i_card(printer.getI_card())
                .serialnumber(printer.getSerialnumber())
                .i_number(printer.getI_number())
                .production(printer.getProduction().getId())
                .model(printer.getModel().getId())
                .itemType(printer.getItemType().getId())
                .name(printer.getName())
                .ip(printer.getIp())
                .type(printer.getType())
                .format(printer.getFormat())
                .printerClass(printer.getPrinterclass())
                .color(printer.getColor())
                .typePechat(printer.getTypepechat())
                .secondSide(printer.getSecondSide())
                .year(printer.getYear())
                .serv(printer.getServ())
                .city(printer.getCity().getId())
                .location(printer.getLocation().getId())
                .room(printer.getRoom())
                .userId(printer.getUser().getId())
                .staydate(printer.getStaydate())
                .price(printer.getPrice())
                .comment(printer.getComment())
                .build();
    }
    private PrinterOutDTO mapToDTO(Printers printer){
        UserDTO userDTO = UserDTO.fromUser(printer.getUser());
        ProductionsDTO productionsDTO = ProductionsDTO.fromProductions(printer.getProduction());
        ModelDTO modelDTO = ModelDTO.fromModel(printer.getModel());
        ItemTypeDTO itemTypeDTO = ItemTypeDTO.fromItemType(printer.getItemType());
        CityDTO cityDTO = CityDTO.fromCity(printer.getCity());
        LocationDTO locationDTO = LocationDTO.fromLocation(printer.getLocation());
        return PrinterOutDTO
                .builder()
                .id(printer.getId())
                .i_card(printer.getI_card())
                .serialnumber(printer.getSerialnumber())
                .i_number(printer.getI_number())
                .production(productionsDTO)
                .model(modelDTO)
                .itemType(itemTypeDTO)
                .name(printer.getName())
                .ip(printer.getIp())
                .type(printer.getType())
                .format(printer.getFormat())
                .printerClass(printer.getPrinterclass())
                .color(printer.getColor())
                .typePechat(printer.getTypepechat())
                .secondSide(printer.getSecondSide())
                .year(printer.getYear())
                .serv(printer.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(printer.getRoom())
                .userId(userDTO)
                .staydate(printer.getStaydate())
                .price(printer.getPrice())
                .comment(printer.getComment())
                .build();
    }
}
