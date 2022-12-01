package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.models.Comune;
import com.ilCary.CarUnit.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ilCary.CarUnit.repo.ComuneRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepo repository;

    public Comune save(Comune x) {
        return repository.save(x);
    }

    public List<Comune> getAll() {
        return repository.findAll();
    }

    public Comune getById(Long id) {

        Optional<Comune> comune = repository.findById(id);

        if(!comune.isPresent())
            throw new NotFoundException("Comune not available");

        return comune.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}