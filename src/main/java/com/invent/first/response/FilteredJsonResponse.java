package com.invent.first.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilteredJsonResponse {
    private Integer id;
    private String item_type;
    private String i_number;
    private Integer i_card;
    private String serialnumber;
    private String production;
    private String model;
    private Double price;
    private Integer room;
    private String serv;
    private String city;
    private Boolean spisano;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date staydate;
    private Integer year;
    private Integer ekp;
    private String street;
    private String number;
    private String firstname;
    private String lastname;
    private String username;

    public FilteredJsonResponse(Tuple tuple) {
        this.id = tuple.get("id",Integer.class);
        this.item_type = tuple.get("item_type",String.class);
        this.i_number = tuple.get("i_number",String.class);
        this.i_card = tuple.get("i_card",Integer.class);
        this.serialnumber = tuple.get("serialnumber", String.class);
        this.production = tuple.get("production", String.class);
        this.model = tuple.get("model",String.class);
        this.price = tuple.get("price",Double.class);
        this.room = tuple.get("room",Integer.class);
        this.serv = tuple.get("serv", String.class);
        this.city = tuple.get("city",String.class);
        this.spisano = tuple.get("spisano",Boolean.class);
        this.staydate = tuple.get("staydate", Date.class);
        this.year = tuple.get("year",Integer.class);
        this.ekp = tuple.get("ekp",Integer.class);
        this.street = tuple.get("street", String.class);
        this.number = tuple.get("number",String.class);
        this.firstname = tuple.get("firstname",String.class);
        this.lastname = tuple.get("lastname",String.class);
        this.username = tuple.get("username",String.class);
    }
}
