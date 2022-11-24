package com.ilCary.CarUnit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "state_adv")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StateAdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private StateElab state;

    private String note;

}
