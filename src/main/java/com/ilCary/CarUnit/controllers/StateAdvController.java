package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.StateAdvConverter;
import com.ilCary.CarUnit.DTOs.StateAdvDTO;
import com.ilCary.CarUnit.models.StateAdv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ilCary.CarUnit.services.StateAdvService;

import java.util.List;

@RestController
@RequestMapping("/api/stateAdv/")
public class StateAdvController {

    private final Logger logger = LoggerFactory.getLogger(StateAdvController.class);

    @Autowired
    private StateAdvService stateAdvService;

    @Autowired
    private StateAdvConverter stateAdvConverter;

    //---------------------------- Post --------------------------------

    @PostMapping
    public StateAdv saveStateAdv(@RequestBody StateAdvDTO dto) {
        StateAdv stateAdv = stateAdvConverter.dtoToEntity(dto);

        logger.info("Save StateAdv in StateAdvController");
        return stateAdvService.save(stateAdv);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<StateAdv> getStateAdvList() {
        return stateAdvService.getAll();
    }

    @GetMapping("{id}")
    public StateAdv getStateAdvById(@PathVariable("id") Long id) {
        return stateAdvService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteStateAdvById(@PathVariable("id") Long id) {
        stateAdvService.deleteById(id);
        return "StateAdv deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public StateAdv updateStateAdv(
            @PathVariable("id") Long id,
            @RequestBody StateAdvDTO dto
    ) {

        StateAdv stateAdv = stateAdvService.getById(id);
        StateAdv updateStateAdv = stateAdvConverter.dtoToEntity(dto);

        if (updateStateAdv.getState() != null)
            stateAdv.setState(updateStateAdv.getState());
        if (updateStateAdv.getNote() != null)
            stateAdv.setNote(updateStateAdv.getNote());
        if (updateStateAdv.getUser() != null)
            stateAdv.setUser(updateStateAdv.getUser());

        stateAdvService.save(stateAdv);
        return stateAdv;
    }

}