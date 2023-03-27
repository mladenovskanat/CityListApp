package com.example.demo7.service.impl;

import com.example.demo7.dto.CityDTO;
import com.example.demo7.dto.CityUpdateRequestDTO;
import com.example.demo7.entity.City;
import com.example.demo7.exception.ResourceNotFoundException;
import com.example.demo7.mapper.CityMapper;
import com.example.demo7.repository.CityRepository;
import com.example.demo7.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    @Override
    public Page<CityDTO> getFilteredData(String name, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<City> pageOfCities;
        pageOfCities = Objects.isNull(name) ? cityRepository.findAll(pageable) : cityRepository.findByNameContainingIgnoreCase(name, pageable);
        if (pageOfCities.getContent().isEmpty()) {
            throw new ResourceNotFoundException("No cities found for this request");
        }
        return pageOfCities.map(cityMapper::cityToCityDTO);
    }

    @Override
    public CityDTO updateCity(CityUpdateRequestDTO request, Long id) {
        City updatedCity = cityRepository.findById(id)
                .map(city -> {
                    city = cityMapper.cityUpdateRequestDTOToCity(request);
                    city.setId(id);
                    return cityRepository.save(city);
                })
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " does not exist."));
        return cityMapper.cityToCityDTO(updatedCity);
    }

    @Override
    public CityDTO getCity(Long id) {
        return cityRepository.findById(id).map(cityMapper::cityToCityDTO)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " does not exist."));    }

}
