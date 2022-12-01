package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.Modello;
import com.ilCary.CarUnit.repo.ModelloRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ModelloService {

    @Autowired
    private ModelloRepo repository;

    public Modello save(Modello x) {
        return repository.save(x);
    }

    public Iterable<Modello> saveAll(Set<Modello> x) {
        return repository.saveAll(x);
    }

    public List<Modello> getAll() {
        return repository.findAll();
    }

    public Modello getById(Long id) {

        Optional<Modello> modello = repository.findById(id);

        if(!modello.isPresent())
            throw new NotFoundException("Modello not available");

        return modello.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}