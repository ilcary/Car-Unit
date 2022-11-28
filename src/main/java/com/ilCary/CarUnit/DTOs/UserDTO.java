package com.ilCary.CarUnit.DTOs;

import com.ilCary.CarUnit.models.Address;
import com.ilCary.CarUnit.models.Dealership;
import com.ilCary.CarUnit.models.Role;
import com.ilCary.CarUnit.models.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private String name;
    private String lastname;
    private String username;
    private String email;
    private List<Task> tasks;
    private Dealership dealership;
    private Address address;

}
