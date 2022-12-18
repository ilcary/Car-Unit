package com.ilCary.CarUnit.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class DealCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private String priceCop;
    private String priceSell;
    private String year;
    private String km;
    private String powerSupply;
    private String gearbox;
    private String emissionClass;
    private String link;

    // tabella che ci collega le immagini a ogni car ad
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = {
            @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "image_id")
            }
    )
    private Set<ImageModel> productImages;

    @JsonBackReference("fleet")
    @ManyToOne
    @JoinColumn(name = "dealership_id")
    private Dealership dealership;

}
