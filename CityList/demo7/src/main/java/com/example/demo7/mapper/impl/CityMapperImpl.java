package com.example.demo7.mapper.impl;

import com.example.demo7.dto.CityDTO;
import com.example.demo7.dto.CityUpdateRequestDTO;
import com.example.demo7.entity.City;
import com.example.demo7.mapper.CityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDTO cityToCityDTO(City city) {
        if (Objects.isNull(city)) {
            return null;
        }
        CityDTO cityDTO = CityDTO.builder().build();
        BeanUtils.copyProperties(city, cityDTO);
        return cityDTO;
    }

    @Override
    public City cityUpdateRequestDTOToCity(CityUpdateRequestDTO cityUpdateRequestDTO) {
        if (Objects.isNull(cityUpdateRequestDTO)) {
            return null;
        }
        City city = City.builder().build();
        BeanUtils.copyProperties(cityUpdateRequestDTO, city, "id");
        return city;
    }


}
