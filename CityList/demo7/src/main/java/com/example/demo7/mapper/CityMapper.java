package com.example.demo7.mapper;

import com.example.demo7.dto.CityDTO;
import com.example.demo7.dto.CityUpdateRequestDTO;
import com.example.demo7.entity.City;

public interface CityMapper {

    CityDTO cityToCityDTO(City city);

    City cityUpdateRequestDTOToCity(CityUpdateRequestDTO cityUpdateRequestDTO);

}
