package com.invent.first.Service.Impl;

import com.invent.first.Repository.GlobalFilterRepository;
import com.invent.first.Service.FilterService;
import com.invent.first.response.FilteredJsonResponse;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@PersistenceContext
@Transactional
public class FilterServiceImpl implements FilterService {
    private final GlobalFilterRepository repository;
    @Autowired
    public FilterServiceImpl(GlobalFilterRepository repository) {
        this.repository = repository;
    }
    //метод для конвертации пустых строк в значение null, чтобы база его воспринимала
    private String convertEmptyStringToNull(String input) {
        return (input != null && input.isEmpty()) ? null : input;
    }

    private Integer convertEmptyStringToNull(Integer input) {
        return (input != null && input == 0) ? null : input;
    }

    private Double convertEmptyStringToNull(Double input) {
        return (input != null && input == 0.0) ? null : input;
    }

    private Boolean convertEmptyStringToNull(Boolean input) {
        return (input != null) ? input : null;
    }

    private Date convertEmptyStringToNull(Date input) {
        return (input != null) ? input : null;
    }

    @Override
    public List<FilteredJsonResponse> getFilteredEqs(String itemType, List<String> iNumbers, Integer iCard, String serialNumber, String production, String model, Double price,
                                                     Integer room, String serv, String city, Boolean spisano, Date staydate, Integer year, Integer ekp, String firstname,
                                                     String lastname, String username) {
        itemType = convertEmptyStringToNull(itemType);
        iNumbers = (iNumbers != null && iNumbers.isEmpty()) ? null : iNumbers;
        iCard = convertEmptyStringToNull(iCard);
        serialNumber = convertEmptyStringToNull(serialNumber);
        production = convertEmptyStringToNull(production);
        model = convertEmptyStringToNull(model);
        price = convertEmptyStringToNull(price);
        room = convertEmptyStringToNull(room);
        serv = convertEmptyStringToNull(serv);
        city = convertEmptyStringToNull(city);
        spisano = convertEmptyStringToNull(spisano);
        staydate = convertEmptyStringToNull(staydate);
        year = convertEmptyStringToNull(year);
        ekp = convertEmptyStringToNull(ekp);
        firstname = convertEmptyStringToNull(firstname);
        lastname = convertEmptyStringToNull(lastname);
        username = convertEmptyStringToNull(username);
        return repository.globalFilterResult(itemType, iNumbers, iCard, serialNumber, production, model,
                price, room, serv, city, spisano, staydate, year, ekp, firstname, lastname, username);
    }
}
