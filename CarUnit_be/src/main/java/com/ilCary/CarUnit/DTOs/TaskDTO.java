package com.ilCary.CarUnit.DTOs;

import com.ilCary.CarUnit.models.User;

import java.time.LocalDate;

public class TaskDTO {

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate deadLine;
    private User user;

}
