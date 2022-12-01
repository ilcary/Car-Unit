package com.ilCary.CarUnit.DTO.converter;


import com.ilCary.CarUnit.DTOs.StarredSearchDTO;
import com.ilCary.CarUnit.models.StarredSearch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StarredSearchConverter {

    public StarredSearchDTO entityToDTO(StarredSearch starredSearch) {

        ModelMapper mapper =new ModelMapper();
        StarredSearchDTO map = mapper.map(starredSearch, StarredSearchDTO.class);
        return map;

    }
    public List<StarredSearchDTO> entityToDTO(List<StarredSearch> starredSearch) {

        return	starredSearch.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public StarredSearch dtoToEntity(StarredSearchDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        StarredSearch map = mapper.map(dto, StarredSearch.class);
        return map;
    }

    public List<StarredSearch> dtoToEntity(List<StarredSearchDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
