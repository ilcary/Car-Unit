package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.StarredSearch;
import com.ilCary.CarUnit.repo.StarredSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarredSearchService {

    @Autowired
    private StarredSearchRepo repository;

    public StarredSearch save(StarredSearch x) {
        return repository.save(x);
    }

    public List<StarredSearch> getAll() {
        return repository.findAll();
    }

    public StarredSearch getById(Long id) {

        Optional<StarredSearch> starredSearch = repository.findById(id);

        if(!starredSearch.isPresent())
            throw new NotFoundException("StarredSearch not available");

        return starredSearch.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}