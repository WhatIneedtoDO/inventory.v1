package com.invent.first.Service;

import com.invent.first.response.FilteredJsonResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface FilterService {
    List<FilteredJsonResponse> getFilteredEqs(String itemType, String iNumber, Integer iCard, String serialNumber, String production,
                                              String model, Double price, Integer room, String serv, String city, Boolean spisano,
                                              Date staydate, Integer year, Integer ekp, String firstname, String lastname, String username);
}
