package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.StarredSearchConverter;
import com.ilCary.CarUnit.DTOs.StarredSearchDTO;
import com.ilCary.CarUnit.models.StarredSearch;
import com.ilCary.CarUnit.models.StateAdv;
import com.ilCary.CarUnit.services.StarredSearchService;
import com.ilCary.CarUnit.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starredSearches")
@CrossOrigin("http://localhost:4200")
public class StarredSearchController {

    private final Logger logger = LoggerFactory.getLogger(StarredSearchController.class);

    @Autowired
    private StarredSearchService starredSearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private StarredSearchConverter starredSearchConverter;

    //---------------------------- Post --------------------------------

    @PostMapping("/{username}")
    public ResponseEntity<StarredSearch> saveStarredSearch(
            @PathVariable("username") String username,
            @RequestBody StarredSearch search
    ) {
        try {
            search.setUser(userService.getUserByUsername(username));
            return new ResponseEntity<>(starredSearchService.save(search), HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<StarredSearch> getStarredSearchList() {
        return starredSearchService.getAll();
    }

    @GetMapping("/{id}")
    public StarredSearch getStarredSearchById(@PathVariable("id") Long id) {
        return starredSearchService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("/{id}")
    public String deleteStarredSearchById(@PathVariable("id") Long id) {
        starredSearchService.deleteById(id);
        return "StarredSearch deleted successfully";
    }

    //---------------------------- Put --------------------------------

//    @PutMapping("{id}")
//    public StarredSearch updateStarredSearch(
//            @PathVariable("id") Long id,
//            @RequestBody StarredSearchDTO dto
//    ) {
//
//        StarredSearch starredSearch = starredSearchService.getById(id);
//        StarredSearch updateStarredSearch = starredSearchConverter.dtoToEntity(dto);
//
//        if (updateStarredSearch.getUser()!= null)
//            starredSearch.setUser(updateStarredSearch.getUser());
//        if (updateStarredSearch.getCambio()!= null)
//            starredSearch.setCambio(updateStarredSearch.getCambio());
//        if (updateStarredSearch.getCarburante()!= null)
//            starredSearch.setCarburante(updateStarredSearch.getCarburante());
//        if (updateStarredSearch.getColore()!= null)
//            starredSearch.setColore(updateStarredSearch.getColore());
//        if (updateStarredSearch.getInserzionista()!= null)
//            starredSearch.setInserzionista(updateStarredSearch.getInserzionista());
//        if (updateStarredSearch.getAnnoImmatricolazioneA()!= null)
//            starredSearch.setAnnoImmatricolazioneA(updateStarredSearch.getAnnoImmatricolazioneA());
//        if (updateStarredSearch.getAnnoImmatricolazioneDa()!= null)
//            starredSearch.setAnnoImmatricolazioneDa(updateStarredSearch.getAnnoImmatricolazioneDa());
//        if (updateStarredSearch.getKmA()!= null)
//            starredSearch.setKmA(updateStarredSearch.getKmA());
//        if (updateStarredSearch.getKmDa()!= null)
//            starredSearch.setKmDa(updateStarredSearch.getKmDa());
//        if (updateStarredSearch.getClasseDiEmissone()!= null)
//            starredSearch.setClasseDiEmissone(updateStarredSearch.getClasseDiEmissone());
//        if (updateStarredSearch.getMarca()!= null)
//            starredSearch.setMarca(updateStarredSearch.getMarca());
//        if (updateStarredSearch.getPorte()!= null)
//            starredSearch.setPorte(updateStarredSearch.getPorte());
//        if (updateStarredSearch.getPrezzoA()!= null)
//            starredSearch.setPrezzoA(updateStarredSearch.getPrezzoA());
//        if (updateStarredSearch.getPrezzoDa()!= null)
//            starredSearch.setPrezzoDa(updateStarredSearch.getPrezzoDa());
//        if (updateStarredSearch.getTipoDiVeicolo()!= null)
//            starredSearch.setTipoDiVeicolo(updateStarredSearch.getTipoDiVeicolo());
//        if (updateStarredSearch.getTipologiaAuto()!= null)
//            starredSearch.setTipologiaAuto(updateStarredSearch.getTipologiaAuto());
//
//        starredSearchService.save(starredSearch);
//        return starredSearch;
//    }

}
