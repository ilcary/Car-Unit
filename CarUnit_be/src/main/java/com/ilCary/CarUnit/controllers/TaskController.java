package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.TaskConverter;
import com.ilCary.CarUnit.DTOs.TaskDTO;
import com.ilCary.CarUnit.models.Task;
import com.ilCary.CarUnit.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks/")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskConverter taskConverter;

    //---------------------------- Post --------------------------------

    @PostMapping
    public Task saveTask(@RequestBody TaskDTO dto) {
        Task task = taskConverter.dtoToEntity(dto);
        task.setStartDate(LocalDate.now());

        logger.info("Save Task in TaskController");
        return taskService.save(task);
    }

    //---------------------------- Get --------------------------------

    @GetMapping
    public List<Task> getTaskList() {
        return taskService.getAll();
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "Task deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public Task updateTask(
            @PathVariable("id") Long id,
            @RequestBody TaskDTO dto
    ) {

        Task task = taskService.getById(id);
        Task updateTask = taskConverter.dtoToEntity(dto);

        if (updateTask.getDescription() != null)
            task.setDescription(updateTask.getDescription());
        if (updateTask.getUser() != null)
            task.setUser(updateTask.getUser());
        if (updateTask.getTitle() != null)
            task.setTitle(updateTask.getTitle());
        if (updateTask.getDeadLine() != null)
            task.setDeadLine(updateTask.getDeadLine());

        taskService.save(task);
        return task;
    }

}