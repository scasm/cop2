package com.example.laboratorinis.usecases;

import com.example.laboratorinis.entities.Author;
import com.example.laboratorinis.entities.Song;
import com.example.laboratorinis.interceptors.LoggedInvocation;
import com.example.laboratorinis.persistence.AuthorsDAO;
import com.example.laboratorinis.persistence.SongsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class SongsForAuthor implements Serializable {

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private SongsDAO songsDAO;

    @Getter @Setter
    private Author author;

    @Getter @Setter
    private Song songToCreate = new Song();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
    }

    @Transactional
    @LoggedInvocation
    public void createSong() {
        songToCreate.setAuthor(this.author);
        songsDAO.persist(songToCreate);
    }
}
