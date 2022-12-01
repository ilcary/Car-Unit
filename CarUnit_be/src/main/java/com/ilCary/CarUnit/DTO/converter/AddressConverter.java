package com.ilCary.CarUnit.DTO.converter;

import com.ilCary.CarUnit.DTOs.AddressDTO;
import com.ilCary.CarUnit.models.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter {

    public AddressDTO entityToDTO(Address address) {

        ModelMapper mapper =new ModelMapper();
        AddressDTO map = mapper.map(address, AddressDTO.class);
        return map;

    }
    public List<AddressDTO> entityToDTO(List<Address> address) {

        return	address.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());

    }


    public Address dtoToEntity(AddressDTO dto)
    {

        ModelMapper mapper = new ModelMapper();
        Address map = mapper.map(dto, Address.class);
        return map;
    }

    public List<Address> dtoToEntity(List<AddressDTO> dto)
    {

        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
