package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.CarAdv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ilCary.CarUnit.repo.CarAdvRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CarAdvService {

    @Autowired
    private CarAdvRepo repository;

    public CarAdv save(CarAdv x) {
        return repository.save(x);
    }

    public List<CarAdv> getAll() {
        return repository.findAll();
    }

    public CarAdv getById(Long id) {

        Optional<CarAdv> carAdv = repository.findById(id);

        if(!carAdv.isPresent())
            throw new NotFoundException("CarAdv not available");

        return carAdv.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
