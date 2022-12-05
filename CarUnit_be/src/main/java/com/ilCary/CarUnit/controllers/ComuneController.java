package com.ilCary.CarUnit.controllers;



import com.ilCary.CarUnit.models.Comune;
import com.ilCary.CarUnit.services.ComuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comuni")
@CrossOrigin("http://localhost:4200")
public class ComuneController {

    private final Logger logger = LoggerFactory.getLogger(ComuneController.class);

    @Autowired
    private ComuneService ComuneService;


    //---------------------------- Get --------------------------------

    @GetMapping
    public List<Comune> getComuneList() {
        return ComuneService.getAll();
    }

    @GetMapping("{id}")
    public Comune getComuneById(@PathVariable("id") Long id) {
        return ComuneService.getById(id);
    }



}
