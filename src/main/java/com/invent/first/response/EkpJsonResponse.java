package com.invent.first.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EkpJsonResponse {

    private Integer equipment_id;
    private String itemtype;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private String production;
    private String model;
    private Integer year;
    private String serv;
    private String city;
    private Integer ekp;
    private String street;
    private String number;
    private Integer room;
    private String firstname;
    private String lastname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date staydate;
    private Double price;
    private String comment;
    private Boolean spisano;

    public EkpJsonResponse(Tuple tuple){
        this.equipment_id = tuple.get("equipment_id", Integer.class);
        this.itemtype = tuple.get("itemtype", String.class);
        this.i_card = tuple.get("i_card", Integer.class);
        this.serialnumber = tuple.get("serialnumber", String.class);
        this.i_number = tuple.get("i_number", String.class);
        this.production = tuple.get("production", String.class);
        this.model = tuple.get("model", String.class);
        this.year = tuple.get("year", Integer.class);
        this.serv = tuple.get("serv", String.class);
        this.city = tuple.get("city", String.class);
        this.ekp = tuple.get("ekp", Integer.class);
        this.street = tuple.get("street", String.class);
        this.number = tuple.get("number", String.class);
        this.room = tuple.get("room", Integer.class);
        this.firstname = tuple.get("firstname", String.class);
        this.lastname = tuple.get("lastname", String.class);
        this.staydate = tuple.get("staydate", Date.class);
        this.price = tuple.get("price", Double.class);
        this.comment = tuple.get("comment", String.class);
        this.spisano = tuple.get("spisano",Boolean.class);
    }
}
