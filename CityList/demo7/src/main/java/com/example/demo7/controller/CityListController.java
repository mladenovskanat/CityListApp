package com.example.demo7.controller;

import com.example.demo7.dto.CityDTO;
import com.example.demo7.dto.CityUpdateRequestDTO;
import com.example.demo7.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "/api/v1/cities")
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CityListController {

    private final CityService cityService;

    @GetMapping("/search")
    public ResponseEntity<Page<CityDTO>> searchCitiesByName(@RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "50") int size) {
        return new ResponseEntity<>(cityService.getFilteredData(name, page, size), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<CityDTO> updateCity(@RequestBody CityUpdateRequestDTO request, @PathVariable Long id) {
        return new ResponseEntity<>(cityService.updateCity(request, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CityDTO> getCity(@PathVariable Long id) {
        return new ResponseEntity<>(cityService.getCity(id), HttpStatus.OK);
    }
}
