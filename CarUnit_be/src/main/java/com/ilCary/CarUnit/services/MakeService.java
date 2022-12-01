package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.Make;
import com.ilCary.CarUnit.repo.MakeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MakeService {

    @Autowired
    private MakeRepo repository;

    public Make save(Make x) {
        Optional<Make> m =  repository.findByNome(x.getNome());
        if (m.isPresent()) {
            return m.get();
        }
        return repository.save(x);
    }


public Optional<Make> findByNome(String nome) {
        return repository.findByNome(nome);
}


    public List<Make> getAll() {
        return repository.findAll();
    }

    public Make getById(Long id) {

        Optional<Make> marchio = repository.findById(id);

        if(!marchio.isPresent())
            throw new NotFoundException("Marchio not available");

        return marchio.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
