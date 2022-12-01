package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.MakeConverter;
import com.ilCary.CarUnit.DTOs.MakeDTO;
import com.ilCary.CarUnit.models.Make;
import com.ilCary.CarUnit.services.MakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makees/")
public class MakeController {

    private final Logger logger = LoggerFactory.getLogger(MakeController.class);

    @Autowired
    private MakeService makeService;

    @Autowired
    private MakeConverter makeConverter;

    //---------------------------- Post --------------------------------

    @PostMapping
    public Make saveMake(@RequestBody MakeDTO dto) {
        Make make = makeConverter.dtoToEntity(dto);

        logger.info("Save Make in MakeController");
        return makeService.save(make);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<Make> getMakeList() {
        return makeService.getAll();
    }

    @GetMapping("{id}")
    public Make getMakeById(@PathVariable("id") Long id) {
        return makeService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteMakeById(@PathVariable("id") Long id) {
        makeService.deleteById(id);
        return "Make deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public Make updateMake(
            @PathVariable("id") Long id,
            @RequestBody MakeDTO dto
    ) {

        Make make = makeService.getById(id);
        Make updateMake = makeConverter.dtoToEntity(dto);

        if (updateMake.getNome() != null)
            make.setNome(updateMake.getNome());



        makeService.save(make);
        return make;
    }

}