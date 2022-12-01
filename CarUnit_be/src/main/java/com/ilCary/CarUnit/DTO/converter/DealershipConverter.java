package com.ilCary.CarUnit.DTO.converter;


import com.ilCary.CarUnit.DTOs.DealershipDTO;
import com.ilCary.CarUnit.models.Dealership;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DealershipConverter {

    public DealershipDTO entityToDTO(Dealership dealership) {

        ModelMapper mapper =new ModelMapper();
        DealershipDTO map = mapper.map(dealership, DealershipDTO.class);
        return map;

    }
    public List<DealershipDTO> entityToDTO(List<Dealership> dealership) {

        return	dealership.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public Dealership dtoToEntity(DealershipDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        Dealership map = mapper.map(dto, Dealership.class);
        return map;
    }

    public List<Dealership> dtoToEntity(List<DealershipDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}

