package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.models.CarAdv;
import com.ilCary.CarUnit.services.CarAdvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/something/") //TODO impostare la rotta
public class CarAdvController {

    private final Logger logger = LoggerFactory.getLogger(CarAdvController.class);

    @Autowired
    private CarAdvService carAdvService;

    //---------------------------- Post --------------------------------

    @PostMapping
    public CarAdv saveCarAdv(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        CarAdv carAdv = CarAdv.builder().build();

        logger.info("Save CarAdv in CarAdvController");
        return carAdvService.save(carAdv);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<CarAdv> getCarAdvList() {
        return carAdvService.getAll();
    }

    @GetMapping("{id}")
    public CarAdv getCarAdvById(@PathVariable("id") Long id) {
        return carAdvService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteCarAdvById(@PathVariable("id") Long id) {
        carAdvService.deleteById(id);
        return "CarAdv deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public CarAdv updateCarAdv(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
    ) {

        CarAdv carAdv = carAdvService.getById(id);

        //TODO gestire il put

        carAdvService.save(carAdv);
        return carAdv;
    }

}