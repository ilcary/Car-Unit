package com.ilCary.CarUnit.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "municipalities")
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comune;
    private String provincia;
    private String cap;
    private String regione;

}
