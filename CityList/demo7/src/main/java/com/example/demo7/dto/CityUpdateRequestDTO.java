package com.example.demo7.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityUpdateRequestDTO {

    private String name;
    private String photo;
}
