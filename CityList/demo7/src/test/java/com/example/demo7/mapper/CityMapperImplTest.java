package com.example.demo7.mapper;

import com.example.demo7.dto.CityDTO;
import com.example.demo7.dto.CityUpdateRequestDTO;
import com.example.demo7.entity.City;
import com.example.demo7.mapper.impl.CityMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CityMapperImplTest {

    @InjectMocks
    private CityMapperImpl cityMapper;

    @Test
    void cityToCityDTO_validParameter_mappedCityDTOReturned() {
        // arrange
        City city = City.builder().id(1L).name("Tokyo").build();

        // act
        CityDTO result = cityMapper.cityToCityDTO(city);

        // assert
        assertThat(result.getId()).isEqualTo(city.getId());
        assertThat(result.getName()).isEqualTo(city.getName());
        assertThat(result.getPhoto()).isEqualTo(city.getPhoto());
    }

    @Test
    void cityToCityDTO_nullParameter_nullReturned() {
        // arrange
        City city = null;

        // act
        CityDTO result = cityMapper.cityToCityDTO(city);

        // assert
        assertThat(result).isNull();
    }

    @Test
    void cityUpdateRequestDTOToCity_validParameter_mappedCityReturned() {
        // arrange
        CityUpdateRequestDTO updateRequestDTO = CityUpdateRequestDTO.builder().name("Tokyo").build();

        // act
        City result = cityMapper.cityUpdateRequestDTOToCity(updateRequestDTO);

        // assert
        assertThat(result.getId()).isNull();
        assertThat(result.getName()).isEqualTo(result.getName());
        assertThat(result.getPhoto()).isEqualTo(result.getPhoto());
    }

    @Test
    void cityUpdateRequestDTOToCity_nullParameter_nullReturned() {
        // arrange
        City city = null;

        // act
        CityDTO result = cityMapper.cityToCityDTO(city);

        // assert
        assertThat(result).isNull();
    }
}
