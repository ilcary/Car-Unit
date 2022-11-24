package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.models.StateAdv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ilCary.CarUnit.services.StateAdvService;

import java.util.List;

@RestController
@RequestMapping("/api/stateAdv/") //TODO impostare la rotta
public class StateAdvController {

    private final Logger logger = LoggerFactory.getLogger(StateAdvController.class);

    @Autowired
    private StateAdvService stateAdvService;

    //---------------------------- Post --------------------------------

    @PostMapping
    public StateAdv saveStateAdv(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        StateAdv stateAdv = StateAdv.builder().build();

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
            @PathVariable("id") Long id
//            @RequestParam("name") String name
    ) {

        StateAdv stateAdv = stateAdvService.getById(id);

        //TODO gestire il put

        stateAdvService.save(stateAdv);
        return stateAdv;
    }

}