package com.example.laboratorinis.persistence;


import com.example.laboratorinis.entities.Author;
import com.example.laboratorinis.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SongsDAO {

    @Inject
    private EntityManager em;

    public List<Song> loadAll() {
        return em.createNamedQuery("Song.findAll", Song.class).getResultList();
    }

    public void persist(Song song){
        this.em.persist(song);
    }

    public Song findOne(Long id){
        return em.find(Song.class, id);
    }

    public Song update(Song song){
        return em.merge(song);
    }
}
