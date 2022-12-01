package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ilCary.CarUnit.repo.DealershipRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DealershipService {

    @Autowired
    private DealershipRepo repository;

    public Dealership save(Dealership x) {
        return repository.save(x);
    }

    public List<Dealership> getAll() {
        return repository.findAll();
    }

    public Dealership getById(Long id) {

        Optional<Dealership> dealership = repository.findById(id);

        if(!dealership.isPresent())
            throw new NotFoundException("Dealership not available");

        return dealership.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
