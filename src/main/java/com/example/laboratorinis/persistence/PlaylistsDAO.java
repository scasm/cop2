package com.example.laboratorinis.persistence;

import com.example.laboratorinis.entities.Playlist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PlaylistsDAO {

    @Inject
    private EntityManager em;

    public List<Playlist> loadAll() {
        return em.createNamedQuery("Playlist.findAll", Playlist.class).getResultList();
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Playlist playlist){
        this.em.persist(playlist);
    }

    public Playlist findOne(Long id) {
        return em.find(Playlist.class, id);
    }


    public Playlist update(Playlist playlist){
        return em.merge(playlist);
    }
}
