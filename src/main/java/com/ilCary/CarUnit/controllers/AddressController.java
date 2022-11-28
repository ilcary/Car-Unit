package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.AddressConverter;
import com.ilCary.CarUnit.DTO.converter.TaskConverter;
import com.ilCary.CarUnit.DTOs.AddressDTO;
import com.ilCary.CarUnit.DTOs.TaskDTO;
import com.ilCary.CarUnit.models.Address;
import com.ilCary.CarUnit.models.Task;
import com.ilCary.CarUnit.services.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/addresses/")
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressConverter addressConverter;

    //---------------------------- Post --------------------------------

    @PostMapping
    public Address saveAddress(@RequestBody AddressDTO dto) {
        Address address = addressConverter.dtoToEntity(dto);

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
            @PathVariable("id") Long id,
            @RequestBody AddressDTO dto
    ) {

        Address address = addressService.getById(id);
        Address updateAddress = addressConverter.dtoToEntity(dto);

        if (updateAddress.getStreet() != null)
            address.setStreet(updateAddress.getStreet());
        if (updateAddress.getMunicipality() != null)
            address.setMunicipality(updateAddress.getMunicipality());
        if (updateAddress.getStreetNum() != null)
            address.setStreetNum(updateAddress.getStreetNum());

        addressService.save(address);
        return address;
    }

}