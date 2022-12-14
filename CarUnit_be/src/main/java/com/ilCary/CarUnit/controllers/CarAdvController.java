package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.Crawler.CrawlerSubito;
import com.ilCary.CarUnit.DTO.converter.CarAdvConverter;
import com.ilCary.CarUnit.DTOs.CarAdvDTO;
import com.ilCary.CarUnit.DTOs.TaskDTO;
import com.ilCary.CarUnit.models.CarAdv;
import com.ilCary.CarUnit.models.Search;
import com.ilCary.CarUnit.services.CarAdvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CarAdv/") //TODO impostare la rotta
@CrossOrigin("http://localhost:4200")
public class CarAdvController {

    private final Logger logger = LoggerFactory.getLogger(CarAdvController.class);

    @Autowired
    private CarAdvService carAdvService;

    @Autowired
    private CarAdvConverter carAdvConverter;

    @Autowired
    private CrawlerSubito crawlerSubito;

    //---------------------------- Post --------------------------------

    @PostMapping
    public CarAdv saveCarAdv(@RequestBody CarAdvDTO dto) {
        CarAdv carAdv = carAdvConverter.dtoToEntity(dto);


        logger.info("Save CarAdv in CarAdvController");
        return carAdvService.save(carAdv);
    }

    @PostMapping("search")
    public List<CarAdv> getCarFromSubito(@RequestBody Search search) throws InterruptedException {
        return crawlerSubito.findCarBySearch(search);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<CarAdv> getCarAdvList() {
        return carAdvService.getAll();
    }



//    @GetMapping("/pageable")
//    public ResponseEntity<Page<User>> findAll(Pageable p) {
//        Page<User> findAll = us.searchAllUsersPageable(p);
//
//        if (findAll.hasContent()) {
//            return new ResponseEntity<>(findAll, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//
//    }

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

        if (updateCarAdv.getTitle() != null)
            carAdv.setTitle(updateCarAdv.getTitle());
        if (updateCarAdv.getPlaceAndDate() != null)
            carAdv.setPlaceAndDate(updateCarAdv.getPlaceAndDate());
        if (updateCarAdv.getPrice() != null)
            carAdv.setPrice(updateCarAdv.getPrice());
        if (updateCarAdv.getCondition() != null)
            carAdv.setCondition(updateCarAdv.getCondition());
        if (updateCarAdv.getYear() != null)
            carAdv.setYear(updateCarAdv.getYear());
        if (updateCarAdv.getLink() != null)
            carAdv.setLink(updateCarAdv.getLink());
        if (updateCarAdv.getPowerSupply() != null)
            carAdv.setPowerSupply(updateCarAdv.getPowerSupply());
        if (updateCarAdv.getGearbox() != null)
            carAdv.setGearbox(updateCarAdv.getGearbox());
        if (updateCarAdv.getEmissionClass() != null)
            carAdv.setEmissionClass(updateCarAdv.getEmissionClass());
        if (updateCarAdv.getPhotoLink() != null)
            carAdv.setPhotoLink(updateCarAdv.getPhotoLink());



        carAdvService.save(carAdv);
        return carAdv;
    }

}