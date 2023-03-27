package com.example.demo7.service;

import com.example.demo7.dto.CityDTO;
import com.example.demo7.dto.CityUpdateRequestDTO;
import org.springframework.data.domain.Page;

public interface CityService {

    Page<CityDTO> getFilteredData(String name, Integer page, Integer size);

    CityDTO updateCity(CityUpdateRequestDTO request, Long id);

    CityDTO getCity(Long id);
}
