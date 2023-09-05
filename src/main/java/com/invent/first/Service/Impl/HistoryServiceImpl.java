package com.invent.first.Service.Impl;

import com.invent.first.DTO.OutDTO.HistoryOutDTO;
import com.invent.first.Entity.HistoryOfChanges;
import com.invent.first.Repository.HistoryRepository;
import com.invent.first.Repository.ItemTypeRepository;
import com.invent.first.Repository.UserRepository;
import com.invent.first.DTO.*;
import com.invent.first.Service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final ProdService prodService;
    private final ModelService modelService;
    private final ItemTypeService itemTypeService;
    private final MotherBProdService motherBProdService;
    private final MotherBModelService motherBModelService;
    private final CpuProdService cpuProdService;
    private final CpuModelService cpuModelService;
    private final CityService cityService;
    private final LocationService locationService;
    private final UserService userService;


    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository, UserRepository userRepository, ItemTypeRepository itemTypeRepository,
                              ProdService prodService, ModelService modelService, ItemTypeService itemTypeService, MotherBProdService motherBProdService,
                              MotherBModelService motherBModelService, CpuProdService cpuProdService, CpuModelService cpuModelService,
                              CityService cityService, LocationService locationService, UserService userService) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.itemTypeRepository = itemTypeRepository;

        this.itemTypeService = itemTypeService;
        this.modelService = modelService;
        this.prodService = prodService;
        this.motherBProdService = motherBProdService;
        this.motherBModelService = motherBModelService;
        this.cpuProdService = cpuProdService;
        this.cpuModelService = cpuModelService;
        this.cityService = cityService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @Override
    public HistoryOutDTO getLastHistory(Integer equipmentId, Integer itemtypeId) {
        HistoryOfChanges historyOfChanges = historyRepository.findByItemTypeAndEquipment(equipmentId, itemtypeId);
        return mapToDTO(historyOfChanges);
    }

    @Override
    public List<HistoryOutDTO> getHistoryList(Integer equipmentId, Integer itemtypeId) {
        List<HistoryOfChanges> historyOfChangesList = historyRepository.findByItemTypeAndEquipmentforList(equipmentId, itemtypeId);
        return mapToDTOs(historyOfChangesList);
    }

    private String getValueAsString(Integer id, String fieldName) {
        switch (fieldName) {
            case "production":
                ProductionsDTO originalProductionDTO = prodService.getProductionById(id);
                return originalProductionDTO.getName();
            case "model":
                ModelDTO originalModelDTO = modelService.getModelById(id);
                return originalModelDTO.getName();
            case "itemType":
                ItemTypeDTO originalItemTypeDTO = itemTypeService.getItemTypeById(id);
                return originalItemTypeDTO.getName();
            case "motherBProd":
                MotherBProdDTO originalMotherProdDTO = motherBProdService.getMotherBProdById(id);
                return originalMotherProdDTO.getName();
            case "motherBModel":
                MotherBModelDTO originalMotherModelDTO = motherBModelService.getMotherModelById(id);
                return originalMotherModelDTO.getName();
            case "cpuproduction":
                CpuProductionDTO originalCpuProd = cpuProdService.getCpuProdById(id);
                return originalCpuProd.getName();
            case "cpumodel":
                CpuModelDTO originalCpuModel = cpuModelService.getCpuModelById(id);
                return originalCpuModel.getName();
            case "city":
                CityDTO originalCityDTO = cityService.getCityById(id);
                return originalCityDTO.getName();
            case "location":
                LocationDTO originalLocation = locationService.getLocationById(id);
                return originalLocation.getEkp() + "," + originalLocation.getStreet() + "," + originalLocation.getNumber();
            case "userId":
                Optional<UserDTO> originalUser = userService.getUserById(id);
                return originalUser.get().getUsername() + "," + originalUser.get().getLastname();
            default:
                return id.toString();
        }
    }

    public String getFieldChangeDetails(Field field, Object originalValue, Object newValue) {

        if (originalValue != null && !originalValue.equals(newValue)) {
            if (originalValue instanceof Date && newValue instanceof Date) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String originalDateStr = dateFormatter.format((Date) originalValue);
                String newDateStr = dateFormatter.format((Date) newValue);

                if (!originalDateStr.equals(newDateStr)) {
                    return field.getName() + " = " + originalDateStr + " -> " + newDateStr;
                }
            } else if (originalValue instanceof Integer) {
                String originalValueStr = getValueAsString((Integer) originalValue, field.getName());
                String newValueStr = getValueAsString((Integer) newValue, field.getName());


                if (!originalValueStr.equals(newValueStr)) {
                    return field.getName() + " = " + originalValueStr + " -> " + newValueStr;
                }
            } else if (originalValue instanceof Enum) {
                Enum<?> originalEnum = (Enum<?>) originalValue;
                Enum<?> newEnum = (Enum<?>) newValue;
                if( originalEnum.name().startsWith("VALUE")&& newEnum.name().startsWith("VALUE")) {
                    String originalEnumValue = originalEnum.name().substring("VALUE".length()); // Используйте метод getValue()
                    String newEnumValue = newEnum.name().substring("VALUE".length()); // Используйте метод getValue()
                    if (!originalEnumValue.equals(newEnumValue)) {
                        return field.getName() + " = " + originalEnumValue + " -> " + newEnumValue;
                    }
                }else {
                    if (!originalEnum.equals(newEnum)) {
                        return field.getName() + " = " + originalEnum + " -> " + newEnum;
                    }
                }

            } else {
                return field.getName() + " = " + originalValue + " -> " + newValue;
            }
        }
        return "";
    }

    public <T> void HistoryObject(T originalObject, T updatedObject, Integer equipmentId, Integer itemType, Integer userId) {
        List<String> changes = new ArrayList<>();
        Field[] fields = originalObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object originalValue = field.get(originalObject);
                Object newValue = field.get(updatedObject);
                String fieldChangeDetails = getFieldChangeDetails(field, originalValue, newValue);
                if (!fieldChangeDetails.isEmpty()) {
                    changes.add(fieldChangeDetails);
                }
            } catch (IllegalAccessException e) {

            }
        }

        String changeDetails = String.join(";", changes);

        HistoryDTO historyDTO = HistoryDTO.builder()
                .user(userId)
                .itemtypeId(itemType)
                .equipmentId(equipmentId)
                .changeDate(new Date())
                .changedetails(changeDetails)
                .build();

        addHistory(historyDTO);
    }

    private List<HistoryOutDTO> mapToDTOs(List<HistoryOfChanges> historyOfChanges) {
        return historyOfChanges.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoryDTO addHistory(HistoryDTO historyDTO) {

        HistoryOfChanges historyOfChanges = mapToEntity(historyDTO);
        HistoryOfChanges newHistory = historyRepository.save(historyOfChanges);
        return mapFromDTO(newHistory);
    }


    private HistoryOfChanges mapToEntity(HistoryDTO historyDTO) {
        HistoryOfChanges historyOfChanges = new HistoryOfChanges();

        historyOfChanges.setUser(userRepository.findById(historyDTO.getUser())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        historyOfChanges.setItemType(itemTypeRepository.findById(historyDTO.getItemtypeId())
                .orElseThrow(() -> new EntityNotFoundException("Item type nor found")));
        historyOfChanges.setEquipmentId(historyDTO.getEquipmentId());
        historyOfChanges.setChangeDate(historyDTO.getChangeDate());
        historyOfChanges.setChangeDetails(historyDTO.getChangedetails());
        return historyOfChanges;
    }

    private HistoryOutDTO mapToDTO(HistoryOfChanges historyOfChanges) {
        UserDTO user = UserDTO.fromUser(historyOfChanges.getUser());
        ItemTypeDTO itemtype = ItemTypeDTO.fromItemType(historyOfChanges.getItemType());
        return HistoryOutDTO
                .builder()
                .id(historyOfChanges.getId())
                .user(user)
                .itemType(itemtype)
                .equipmentId(historyOfChanges.getEquipmentId())
                .changedate(historyOfChanges.getChangeDate())
                .details(historyOfChanges.getChangeDetails())
                .build();
    }


    private HistoryDTO mapFromDTO(HistoryOfChanges historyOfChanges) {
        return HistoryDTO
                .builder()
                .id(historyOfChanges.getId())
                .user(historyOfChanges.getUser().getId())
                .itemtypeId(historyOfChanges.getItemType().getId())
                .equipmentId(historyOfChanges.getEquipmentId())
                .changedetails(historyOfChanges.getChangeDetails())
                .changeDate(historyOfChanges.getChangeDate())
                .build();
    }


}
