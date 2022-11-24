package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.StateAdv;
import com.ilCary.CarUnit.repo.StateAdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateAdvService {

    @Autowired
    private StateAdvRepo repository;

    public StateAdv save(StateAdv x) {
        return repository.save(x);
    }

    public List<StateAdv> getAll() {
        return repository.findAll();
    }

    public StateAdv getById(Long id) {

        Optional<StateAdv> stateAdv = repository.findById(id);

        if(!stateAdv.isPresent())
            throw new NotFoundException("StateAdv not available");

        return stateAdv.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
