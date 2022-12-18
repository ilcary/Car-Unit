package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.DealershipConverter;
import com.ilCary.CarUnit.DTO.converter.UserConverter;
import com.ilCary.CarUnit.DTOs.DealershipDTO;
import com.ilCary.CarUnit.models.Dealership;
import com.ilCary.CarUnit.models.User;
import com.ilCary.CarUnit.services.DealershipService;
import com.ilCary.CarUnit.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealerships")
@CrossOrigin("http://localhost:4200")
public class DealershipController {

    private final Logger logger = LoggerFactory.getLogger(DealershipController.class);

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private DealershipConverter dealershipConverter;

    @Autowired
    private UserService userService;

    //---------------------------- Post --------------------------------

    @PostMapping("{userCeo_id}")
    public Dealership saveDealership(
            @PathVariable("userCeo_id") Long userCeo_id,
            @RequestBody Dealership dealership
    ) {
        User user = userService.getById(userCeo_id);
        dealership.setCeo(user);
        logger.info("Save Dealership in DealershipController");
        dealershipService.save(dealership);//verco in tutti i concessionari quelli che hanno nella colonna ceo_id l'id dello user che gli passo così so di che concessionario è padrone top
        return dealership;
    }

    //---------------------------- Get --------------------------------

    @GetMapping("/userToDealership/{user_id}/{deal_id}")
    public Dealership addUserToDealership(
            @PathVariable("user_id") Long user_id,
            @PathVariable("deal_id") Long deal_id
    ) {
        Dealership dealership = dealershipService.getById(deal_id);
        User user = userService.getById(user_id);
        user.setDealership(dealership);
        dealership.addUserToDealership(user);
        userService.update(user);
        logger.info("User "+user.getName()+" added as employ at "+dealership.getName());
        return dealershipService.save(dealership);
    }

    @GetMapping("byCeoId/{id}")
    public Dealership getDealershipByCeoId(@PathVariable("id") Long id){
        if (dealershipService.getDealershipByCeoId(id).isPresent()){
            return dealershipService.getDealershipByCeoId(id).get();
        }
        logger.error("no dealership found for the given ceo id: "+id);
        return null;
    }

    @GetMapping
    public List<Dealership> getDealershipList() {
        return dealershipService.getAll();
    }

    @GetMapping("{id}")
    public Dealership getDealershipById(@PathVariable("id") Long id) {
        return dealershipService.getById(id);
    }

    @GetMapping("Employees/{id}")
    public List<User> getDealershipEmployees(@PathVariable("id") Long id) {
        return dealershipService.getById(id).getEmployees();
    }

    @GetMapping("DealershipByEmployeesId/{id}")
    public Dealership getDealershipByEmployeesId(@PathVariable("id") Long user_id) {
        return userService.getById(user_id).getDealership();

    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteDealershipById(@PathVariable("id") Long id) {
        dealershipService.deleteById(id);
        return "Dealership deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public Dealership updateDealership(
            @PathVariable("id") Long id,
            @RequestBody DealershipDTO dto
    ) {

        Dealership dealership = dealershipService.getById(id);
        Dealership updateDealership = dealershipConverter.dtoToEntity(dto);

        if (updateDealership.getAddress() != null)
            dealership.setAddress(updateDealership.getAddress());
        if (updateDealership.getCeo() != null)
            dealership.setCeo(updateDealership.getCeo());
        if (updateDealership.getAddress() != null)
            dealership.setName(updateDealership.getName());
        if (updateDealership.getEmployees()!= null)
            dealership.setEmployees(updateDealership.getEmployees());


        dealershipService.save(dealership);
        return dealership;
    }

}
