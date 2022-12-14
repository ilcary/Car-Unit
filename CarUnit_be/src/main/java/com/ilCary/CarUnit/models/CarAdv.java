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

    private String title;
    private String placeAndDate;

    @Override
    public String toString() {
        return "CarAdv{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", placeAndDate='" + placeAndDate + '\'' +
                ", price='" + price + '\'' +
                ", condition='" + condition + '\'' +
                ", year='" + year + '\'' +
                ", powerSupply='" + powerSupply + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", emissionClass='" + emissionClass + '\'' +
                ", link='" + link + '\'' +
                ", photoLink='" + photoLink + '\'' +
                '}';
    }

    private String price;
    private String condition;
    private String year;

    private String km;
    private String powerSupply;
    private String gearbox;
    private String emissionClass;
    private String link;
    private String photoLink;

//    Classe a w176 automatic premium
//    Gallarate (VA)12 nov alle 14:50
//    20.000 €
//    Usato
//    12/2016
//    90000 Km
//    Diesel
//    Automatico
//    Euro 6

    //  to display on the card of the ad the processing status, notes and who done it




}
