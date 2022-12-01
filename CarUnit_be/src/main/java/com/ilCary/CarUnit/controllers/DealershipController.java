package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.DealershipConverter;
import com.ilCary.CarUnit.DTOs.DealershipDTO;
import com.ilCary.CarUnit.models.Dealership;
import com.ilCary.CarUnit.services.DealershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealerships/") //TODO impostare la rotta
public class DealershipController {

    private final Logger logger = LoggerFactory.getLogger(DealershipController.class);

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private DealershipConverter dealershipConverter;

    //---------------------------- Post --------------------------------

    @PostMapping
    public Dealership saveDealership(@RequestBody DealershipDTO dto) {
        Dealership dealership = dealershipConverter.dtoToEntity(dto);

        logger.info("Save Dealership in DealershipController");
        return dealershipService.save(dealership);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<Dealership> getDealershipList() {
        return dealershipService.getAll();
    }

    @GetMapping("{id}")
    public Dealership getDealershipById(@PathVariable("id") Long id) {
        return dealershipService.getById(id);
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
