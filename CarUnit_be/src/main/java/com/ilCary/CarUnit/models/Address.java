package com.ilCary.CarUnit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street; //via

    private String streetNum;//civico


    @ManyToOne
    private Comune municipality; //comune

}
