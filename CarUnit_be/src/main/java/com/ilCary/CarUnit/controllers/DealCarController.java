package com.ilCary.CarUnit.controllers;
import com.ilCary.CarUnit.models.DealCar;
import com.ilCary.CarUnit.models.Dealership;
import com.ilCary.CarUnit.models.ImageModel;
import com.ilCary.CarUnit.services.DealCarService;
import com.ilCary.CarUnit.services.DealershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/DealCar")
@CrossOrigin("http://localhost:4200")
public class DealCarController {

    private final Logger logger = LoggerFactory.getLogger(DealCarController.class);

    @Autowired
    private DealCarService dealCarService;
    @Autowired
    private DealershipService dealershipService;

    @PostMapping("{id}")
    public DealCar saveDealCar(
        @RequestBody DealCar car,
        @PathVariable("id") Long deal_id
    ) {
      Dealership d = dealershipService.getById(deal_id);
      car.setDealership(d);

        logger.info("Save DealCar in DealCarController");
        return dealCarService.save(car);
    }

    @PostMapping(value= {"/addNewDealCar/{id}"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public DealCar addNewDealCar(@RequestPart("dealCar") DealCar car,
                                 @RequestPart("imageFile") MultipartFile[] file,
                                 @PathVariable("id") Long deal_id){
        Dealership d = dealershipService.getById(deal_id);
        car.setDealership(d);

        try {
            Set<ImageModel> images = uploadImage(file);
            car.setProductImages(images);
            return  dealCarService.save(car);
        }catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
        }

    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for(MultipartFile file : multipartFiles){
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @GetMapping
    public List<DealCar> getDealCarList() {
        return dealCarService.getAll();
    }

    @GetMapping("{id}")
    public DealCar getDealCarById(@PathVariable("id") Long id) {
        return dealCarService.getById(id);
    }

    @GetMapping("/getAllDealCar/{id}")
    public List<DealCar> getAllDealCar(@PathVariable("id") Long deal_id) {
     return dealCarService.getAllByDealershipId(deal_id);
    }

    @DeleteMapping("{id}")
    public String deleteDealCarById(@PathVariable("id") Long id) {
        dealCarService.deleteById(id);
        return "DealCar deleted successfully";
    }

    @PutMapping("{id}")
    public DealCar updateDealCar(
            @PathVariable("id") Long id,
            @RequestBody DealCar updateCar
    ) {
        System.out.println(updateCar);
        DealCar dealCar = dealCarService.getById(id);

        if (updateCar.getPriceSell() != null)
            dealCar.setPriceSell(updateCar.getPriceSell());

        dealCarService.save(dealCar);
        return dealCar;
    }

}