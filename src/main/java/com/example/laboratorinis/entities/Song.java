package com.example.laboratorinis.entities;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Song.findAll", query = "select t from Song as t")
})
@Entity
public class Song {
    private Long id;

    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String title;

    @Basic(optional = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String genre;

    @Basic
    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.genre = genre;}
    private Author author;

    @ManyToOne(optional = false)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    private List<Playlist> playlists;

    @ManyToMany
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
