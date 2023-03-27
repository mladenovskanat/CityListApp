package com.example.demo7.service;

import com.example.demo7.dto.CityDTO;
import com.example.demo7.dto.CityUpdateRequestDTO;
import com.example.demo7.entity.City;
import com.example.demo7.exception.ResourceNotFoundException;
import com.example.demo7.mapper.impl.CityMapperImpl;
import com.example.demo7.repository.CityRepository;
import com.example.demo7.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapperImpl cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void updateCity_validParameters_updatedCityReturned() {
        // arrange
        Long id = 1L;
        CityUpdateRequestDTO cityUpdateRequest = CityUpdateRequestDTO.builder().name("new name").photo("new photo").build();
        City city = City.builder().id(1L).name("name").photo("photo").build();
        CityDTO cityDTO = CityDTO.builder().id(1L).name("new name").photo("new photo").build();
        when(cityRepository.findById(anyLong())).thenReturn(Optional.ofNullable(city));
        when(cityRepository.save(any(City.class))).thenReturn(city);
        when(cityMapper.cityToCityDTO(any(City.class))).thenReturn(cityDTO);
        when(cityMapper.cityUpdateRequestDTOToCity(any(CityUpdateRequestDTO.class))).thenReturn(city);

        // act
        CityDTO actualCity = cityService.updateCity(cityUpdateRequest, id);

        // assert
        assertThat(actualCity.getName()).isEqualTo(cityUpdateRequest.getName());
        assertThat(actualCity.getPhoto()).isEqualTo(cityUpdateRequest.getPhoto());
    }

    @Test
    void updateCity_invalidParameters_shouldThrowException() {
        // arrange
        Long id = 1L;
        CityUpdateRequestDTO cityUpdateRequest = CityUpdateRequestDTO.builder().name("new name").photo("new photo").build();

        // act
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> cityService.updateCity(cityUpdateRequest, id));

        // assert
        assertEquals("City with id " + id + " does not exist.", exception.getMessage());
    }

    @Test
    void getFilteredData_validNameParameter_shouldReturnListOfCities() {
        // arrange
        City city = City.builder().id(1L).name("name").photo("photo").build();
        String name = "Tokyo";
        List<City> cities = List.of(city);
        final Page<City> page = new PageImpl<>(cities);
        when(cityRepository.findByNameContainingIgnoreCase(any(), any())).thenReturn(page);

        // act
        Page<CityDTO> response = cityService.getFilteredData(name, 0, 50);

        // assert
        assertThat(response.getContent()).hasSize(1);
    }

    @Test
    void getFilteredData_noParameters_shouldReturnListOfCities() {
        // arrange
        City city = City.builder().id(1L).name("name").photo("photo").build();
        List<City> cities = List.of(city);
        String name = null;
        final Page<City> page = new PageImpl<>(cities);
        when(cityRepository.findAll(any(Pageable.class))).thenReturn(page);

        // act
        Page<CityDTO> response = cityService.getFilteredData(name, 0, 50);

        // assert
        assertThat(response.getContent()).hasSize(1);
    }

    @Test
    void getFilteredData_invalidParameters_shouldThrowException() {
        // arrange
        String name = "Tokyo";
        final Page<City> page = new PageImpl<>(new ArrayList<>());
        when(cityRepository.findByNameContainingIgnoreCase(any(), any())).thenReturn(page);

        // act
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> cityService.getFilteredData(name, 0, 50));

        // assert
        assertEquals("No cities found for this request", exception.getMessage());
    }
}
