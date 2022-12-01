package com.ilCary.CarUnit.models;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "modelli")
@NoArgsConstructor
@AllArgsConstructor
public class Modello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;
}
