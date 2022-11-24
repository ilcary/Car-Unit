package com.ilCary.CarUnit.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dealerships")
@Data
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ceo_id")
    private User ceo;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

  //  private List<CarLocal> fleet;

    private List<User> employees;


}
