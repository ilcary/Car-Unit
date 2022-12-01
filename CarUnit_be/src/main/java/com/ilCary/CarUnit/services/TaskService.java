package com.ilCary.CarUnit.services;

import com.ilCary.CarUnit.exception.NotFoundException;
import com.ilCary.CarUnit.models.Task;
import com.ilCary.CarUnit.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo repository;

    public Task save(Task x) {
        return repository.save(x);
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {

        Optional<Task> task = repository.findById(id);

        if(task.isEmpty())
            throw new NotFoundException("Task not available");

        return task.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
