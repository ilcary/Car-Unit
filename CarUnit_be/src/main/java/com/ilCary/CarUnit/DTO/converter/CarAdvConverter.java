package com.ilCary.CarUnit.DTO.converter;

import com.ilCary.CarUnit.DTOs.CarAdvDTO;
import com.ilCary.CarUnit.models.CarAdv;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarAdvConverter {

    public CarAdvDTO entityToDTO(CarAdv carAdv) {

        ModelMapper mapper =new ModelMapper();
        CarAdvDTO map = mapper.map(carAdv, CarAdvDTO.class);
        return map;

    }
    public List<CarAdvDTO> entityToDTO(List<CarAdv> carAdv) {

        return	carAdv.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public CarAdv dtoToEntity(CarAdvDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        CarAdv map = mapper.map(dto, CarAdv.class);
        return map;
    }

    public List<CarAdv> dtoToEntity(List<CarAdvDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
