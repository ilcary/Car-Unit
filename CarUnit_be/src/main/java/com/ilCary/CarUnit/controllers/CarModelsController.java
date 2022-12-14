package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.Crawler.CrawlerSubitoModels;
import com.ilCary.CarUnit.models.CarAdv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CarModels/")
@CrossOrigin("http://localhost:4200")
public class CarModelsController {

    @Autowired
    CrawlerSubitoModels csm;

    @GetMapping("{make}")
    public List<String> getCarAdvList(@PathVariable(name="make") String make) throws InterruptedException {

        return csm.getAllModelsOf(make);
    }
}
