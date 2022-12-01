package com.ilCary.CarUnit.DTO.converter;


import com.ilCary.CarUnit.DTOs.UserDTO;
import com.ilCary.CarUnit.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDTO entityToDTO(User user) {

        ModelMapper mapper =new ModelMapper();
        UserDTO map = mapper.map(user, UserDTO.class);
        return map;

    }
    public List<UserDTO> entityToDTO(List<User> user) {

        return	user.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public User dtoToEntity(UserDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        User map = mapper.map(dto, User.class);
        return map;
    }

    public List<User> dtoToEntity(List<UserDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
