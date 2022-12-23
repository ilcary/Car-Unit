package com.ilCary.CarUnit.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Date startDate;
    private Date deadLine;

    private boolean done;

    @ManyToOne
    @JsonBackReference("task")
    @JoinColumn(name = "user_id")
    private User user;


}
