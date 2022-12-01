package com.ilCary.CarUnit.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate deadLine;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
