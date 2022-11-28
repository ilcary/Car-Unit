package com.ilCary.CarUnit.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "car_adv")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CarAdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Integer kilometers;
    private Integer price;
    private String link;
    private Market market;



    //  to display on the card of the ad the processing status, notes and who done it
    @ManyToOne
    @JoinColumn(name = "state_adv_id")
    private StateAdv stateAdv;



}
