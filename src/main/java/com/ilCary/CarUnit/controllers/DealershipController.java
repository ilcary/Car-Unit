package com.ilCary.CarUnit.controllers;

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

    //---------------------------- Post --------------------------------

    @PostMapping
    public Dealership saveDealership(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        Dealership dealership = Dealership.builder().build();

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
            @PathVariable("id") Long id
//            @RequestParam("name") String name
    ) {

        Dealership dealership = dealershipService.getById(id);

        //TODO gestire il put

        dealershipService.save(dealership);
        return dealership;
    }

}
