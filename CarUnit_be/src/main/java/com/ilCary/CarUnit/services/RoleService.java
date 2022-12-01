package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ilCary.CarUnit.repo.RoleRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepo repository;

    public Role save(Role x) {
        return repository.save(x);
    }

    public List<Role> getAll() {
        return repository.findAll();
    }

    public Role getById(Long id) {

        Optional<Role> role = repository.findById(id);

        if(!role.isPresent())
            throw new NotFoundException("Role not available");

        return role.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}