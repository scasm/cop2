package com.example.laboratorinis.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Getter @Setter
@JsonbPropertyOrder({"id", "title", "genre"})
public class SongDTO {
    private Long id;
    private String title;
    private String genre;
}
