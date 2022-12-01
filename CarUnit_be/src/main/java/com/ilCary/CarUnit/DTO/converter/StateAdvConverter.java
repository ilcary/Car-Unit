package com.ilCary.CarUnit.DTO.converter;


import com.ilCary.CarUnit.DTOs.StateAdvDTO;
import com.ilCary.CarUnit.models.StateAdv;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateAdvConverter {

    public StateAdvDTO entityToDTO(StateAdv stateAdv) {

        ModelMapper mapper =new ModelMapper();
        StateAdvDTO map = mapper.map(stateAdv, StateAdvDTO.class);
        return map;

    }
    public List<StateAdvDTO> entityToDTO(List<StateAdv> stateAdv) {

        return	stateAdv.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public StateAdv dtoToEntity(StateAdvDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        StateAdv map = mapper.map(dto, StateAdv.class);
        return map;
    }

    public List<StateAdv> dtoToEntity(List<StateAdvDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}