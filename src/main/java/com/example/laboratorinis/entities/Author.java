package com.example.laboratorinis.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
@Entity
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "select t from Author as t")
})
@EqualsAndHashCode
public class Author {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic(optional = false)
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic(optional = false)
    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @OneToMany(mappedBy = "author")
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
