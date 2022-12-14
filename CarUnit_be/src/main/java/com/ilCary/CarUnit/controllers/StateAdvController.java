package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.StateAdvConverter;
import com.ilCary.CarUnit.DTOs.StateAdvDTO;
import com.ilCary.CarUnit.models.StateAdv;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ilCary.CarUnit.services.StateAdvService;

import java.util.List;

@RestController
@RequestMapping("/api/stateAdv")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class StateAdvController {

    private final Logger logger = LoggerFactory.getLogger(StateAdvController.class);

    @Autowired
    private StateAdvService stateAdvService;

    @Autowired
    private StateAdvConverter stateAdvConverter;

    //---------------------------- Post --------------------------------

    @PostMapping
    public StateAdv saveStateAdv(@RequestBody StateAdv stateAdv) {

        logger.info("Save StateAdv in StateAdvController");
        return stateAdvService.save(stateAdv);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<StateAdv> getStateAdvList() {
        return stateAdvService.getAll();
    }

    @GetMapping("/getStateByid")
    public StateAdv getStateAdvById(@RequestParam(name = "id") String id) {
        return stateAdvService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping()
    public String deleteStateAdvById(@RequestParam(name = "id") String id) {
        stateAdvService.deleteById(id);
        return "StateAdv deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("/update")
    public StateAdv updateStateAdv(
            @RequestBody StateAdv updateStateAdv
    ) {
        logger.info(updateStateAdv.toString());
        logger.info(updateStateAdv.getId());
        StateAdv stateAdv = stateAdvService.getById(updateStateAdv.getId());

        if (updateStateAdv.getState() != null)
            stateAdv.setState(updateStateAdv.getState());
        if (updateStateAdv.getNote() != null)
            stateAdv.setNote(updateStateAdv.getNote());
        if (updateStateAdv.getUsername() != null)
            stateAdv.setUsername(updateStateAdv.getUsername());

        stateAdvService.save(stateAdv);
        return stateAdv;
    }

}