package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.models.Role;
import com.ilCary.CarUnit.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles/") //TODO impostare la rotta
public class RoleController {

    private final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    //---------------------------- Post --------------------------------

    @PostMapping
    public Role saveRole(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        Role role = Role.builder().build();

        logger.info("Save Role in RoleController");
        return roleService.save(role);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<Role> getRoleList() {
        return roleService.getAll();
    }

    @GetMapping("{id}")
    public Role getRoleById(@PathVariable("id") Long id) {
        return roleService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteRoleById(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return "Role deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public Role updateRole(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
    ) {

        Role role = roleService.getById(id);

        //TODO gestire il put

        roleService.save(role);
        return role;
    }

}
