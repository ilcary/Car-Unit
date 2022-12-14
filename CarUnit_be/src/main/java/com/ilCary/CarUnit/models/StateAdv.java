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
    private String id;

    private String username;

    @Enumerated(EnumType.STRING)
    private StateElab state;

    private String note;

    @Override
    public String toString() {
        return "StateAdv{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", state=" + state +
                ", note='" + note + '\'' +
                '}';
    }

}
