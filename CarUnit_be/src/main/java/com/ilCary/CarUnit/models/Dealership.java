package com.ilCary.CarUnit.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dealerships")
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


    @OneToOne
    @JoinColumn(name = "ceo_id")
    private User ceo;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

  //  private List<CarLocal> fleet;

    @OneToMany(mappedBy="dealership")
    @JsonManagedReference
    private List<User> employees;

    @JsonManagedReference("fleet")
    @OneToMany(mappedBy="dealership")
    private List<DealCar> fleet;


    public void addUserToDealership(User u){
       employees.add(u);
    }

    public void removeUserFromDealership(User u){
        if(employees.contains(u)){
            employees.remove(u);
        }else{
            System.out.println("user not contained in employees");
        }

    }

}
