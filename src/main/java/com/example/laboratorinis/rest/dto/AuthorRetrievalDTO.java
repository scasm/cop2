package com.example.laboratorinis.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@Getter @Setter
@JsonbPropertyOrder({"name", "surname", "nationality", "songs"})
public class AuthorRetrievalDTO {
    private String name;
    private String surname;
    private String nationality;
    private List<SongDTO> songs;
}
