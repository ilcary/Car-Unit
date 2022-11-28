package com.ilCary.CarUnit.DTO.converter;


import com.ilCary.CarUnit.DTOs.RoleDTO;
import com.ilCary.CarUnit.models.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    public RoleDTO entityToDTO(Role role) {

        ModelMapper mapper =new ModelMapper();
        RoleDTO map = mapper.map(role, RoleDTO.class);
        return map;

    }
    public List<RoleDTO> entityToDTO(List<Role> role) {

        return	role.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public Role dtoToEntity(RoleDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        Role map = mapper.map(dto, Role.class);
        return map;
    }

    public List<Role> dtoToEntity(List<RoleDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
