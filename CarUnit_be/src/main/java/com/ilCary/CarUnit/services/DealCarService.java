package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.DealCar;
import com.ilCary.CarUnit.repo.DealCarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealCarService {

    @Autowired
    private DealCarRepo repository;

    public DealCar save(DealCar x) {
        return repository.save(x);
    }

    public List<DealCar> getAll() {
        return repository.findAll();
    }

    public List<DealCar> getAllByDealershipId(Long  id) {
        return repository.findByDealership_Id(id);
    }

    public DealCar getById(Long id) {

        Optional<DealCar> DealCar = repository.findById(id);

        if(!DealCar.isPresent())
            throw new NotFoundException("DealCar not available");

        return DealCar.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
