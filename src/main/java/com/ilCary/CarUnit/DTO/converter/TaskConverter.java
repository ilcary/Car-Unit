package com.ilCary.CarUnit.DTO.converter;

import com.ilCary.CarUnit.DTOs.TaskDTO;
import com.ilCary.CarUnit.models.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskConverter {

    public TaskDTO entityToDTO(Task task) {

        ModelMapper mapper =new ModelMapper();
        TaskDTO map = mapper.map(task, TaskDTO.class);
        return map;

    }
    public List<TaskDTO> entityToDTO(List<Task> task) {

        return	task.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public Task dtoToEntity(TaskDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        Task map = mapper.map(dto, Task.class);
        return map;
    }

    public List<Task> dtoToEntity(List<TaskDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}

