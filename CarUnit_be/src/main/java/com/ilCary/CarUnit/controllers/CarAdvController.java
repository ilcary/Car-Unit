package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.CarAdvConverter;
import com.ilCary.CarUnit.DTOs.CarAdvDTO;
import com.ilCary.CarUnit.DTOs.TaskDTO;
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

    @Autowired
    private CarAdvConverter carAdvConverter;

    //---------------------------- Post --------------------------------

    @PostMapping
    public CarAdv saveCarAdv(@RequestBody CarAdvDTO dto) {
        CarAdv carAdv = carAdvConverter.dtoToEntity(dto);


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
            @PathVariable("id") Long id,
            @RequestBody CarAdvDTO dto
    ) {

        CarAdv carAdv = carAdvService.getById(id);
        CarAdv updateCarAdv = carAdvConverter.dtoToEntity(dto);

        if (updateCarAdv.getDescription() != null)
            carAdv.setDescription(updateCarAdv.getDescription());
        if (updateCarAdv.getDateProduced() != null)
            carAdv.setDateProduced(updateCarAdv.getDateProduced());
        if (updateCarAdv.getEmail() != null)
            carAdv.setEmail(updateCarAdv.getEmail());
        if (updateCarAdv.getPrice() != null)
            carAdv.setPrice(updateCarAdv.getPrice());
        if (updateCarAdv.getPower() != null)
            carAdv.setPower(updateCarAdv.getPower());
        if (updateCarAdv.getLink() != null)
            carAdv.setLink(updateCarAdv.getLink());
        if (updateCarAdv.getKilometers() != null)
            carAdv.setKilometers(updateCarAdv.getKilometers());
        if (updateCarAdv.getPhoneNumber() != null)
            carAdv.setPhoneNumber(updateCarAdv.getPhoneNumber());
        if (updateCarAdv.getFuelType() != null)
            carAdv.setFuelType(updateCarAdv.getFuelType());
        if (updateCarAdv.getMakeAndModel() != null)
            carAdv.setMakeAndModel(updateCarAdv.getMakeAndModel());
        if (updateCarAdv.getMarket() != null)
            carAdv.setMarket(updateCarAdv.getMarket());
        if (updateCarAdv.getLocation() != null)
            carAdv.setLocation(updateCarAdv.getLocation());
        if (updateCarAdv.getOwnerName() != null)
            carAdv.setOwnerName(updateCarAdv.getOwnerName());
        if (updateCarAdv.getPreparation() != null)
            carAdv.setPreparation(updateCarAdv.getPreparation());
        if (updateCarAdv.getPreparation() != null)
            carAdv.setPreparation(updateCarAdv.getPreparation());
        if (updateCarAdv.getStateAdv() != null)
            carAdv.setStateAdv(updateCarAdv.getStateAdv());


        carAdvService.save(carAdv);
        return carAdv;
    }

}