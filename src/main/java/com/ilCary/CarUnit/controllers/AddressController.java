package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.models.Address;
import com.ilCary.CarUnit.services.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses/") //TODO impostare la rotta
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    //---------------------------- Post --------------------------------

    @PostMapping
    public Address saveAddress(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        Address address = Address.builder().build();

        logger.info("Save Address in AddressController");
        return addressService.save(address);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<Address> getAddressList() {
        return addressService.getAll();
    }

    @GetMapping("{id}")
    public Address getAddressById(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteAddressById(@PathVariable("id") Long id) {
        addressService.deleteById(id);
        return "Address deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public Address updateAddress(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
    ) {

        Address address = addressService.getById(id);

        //TODO gestire il put

        addressService.save(address);
        return address;
    }

}