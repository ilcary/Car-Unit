package com.ilCary.CarUnit.DTO.converter;


import com.ilCary.CarUnit.DTOs.MakeDTO;
import com.ilCary.CarUnit.models.Make;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MakeConverter {

    public MakeDTO entityToDTO(Make make) {

        ModelMapper mapper =new ModelMapper();
        MakeDTO map = mapper.map(make, MakeDTO.class);
        return map;

    }
    public List<MakeDTO> entityToDTO(List<Make> make) {

        return	make.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public Make dtoToEntity(MakeDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        Make map = mapper.map(dto, Make.class);
        return map;
    }

    public List<Make> dtoToEntity(List<MakeDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
