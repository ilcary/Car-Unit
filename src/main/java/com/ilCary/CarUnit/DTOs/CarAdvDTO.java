package com.ilCary.CarUnit.DTOs;

import com.ilCary.CarUnit.models.Market;
import com.ilCary.CarUnit.models.StateAdv;

import java.time.LocalDate;

public class CarAdvDTO {

    private String ownerName;
    private String email;
    private String phoneNumber;
    private String makeAndModel;
    private String preparation;
    private String description;
    private String fuelType;
    private String power;
    private String location;
    private LocalDate dateProduced;
    private int kilometers;
    private int price;
    private String link;
    private Market market;
    private StateAdv stateAdv;
}
