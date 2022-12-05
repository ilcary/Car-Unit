package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.User;
import com.ilCary.CarUnit.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repository;
    @Autowired
    PasswordEncoder encoder;

    public User save(User x) {
        x.setPassword(encoder.encode(x.getPassword()));
        return repository.save(x);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {

        Optional<User> user = repository.findById(id);

        if(!user.isPresent())
            throw new NotFoundException("User not available");

        return user.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}