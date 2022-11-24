package com.ilCary.CarUnit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_adv")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CarAdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int kilometers;
    private String dateProduced;
    private int price;
    private String email;
    private int phoneNumber;
    // vedere come implementare le foto
    // bisognerà aggiungere qualche altra propietà in base a ciò che ci danno le api


    //  to display on the card of the ad the processing status, notes and who done it
    @ManyToOne
    @JoinColumn(name = "state_adv_id")
    private StateAdv stateAdv;



}
