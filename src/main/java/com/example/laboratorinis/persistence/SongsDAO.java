package com.example.laboratorinis.persistence;


import com.example.laboratorinis.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SongsDAO {

    @Inject
    private EntityManager em;

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
