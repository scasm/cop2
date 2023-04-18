package com.example.laboratorinis.usecases;

import com.example.laboratorinis.entities.Author;
import com.example.laboratorinis.entities.Playlist;
import com.example.laboratorinis.entities.Song;
import com.example.laboratorinis.interceptors.LoggedInvocation;
import com.example.laboratorinis.persistence.AuthorsDAO;
import com.example.laboratorinis.persistence.PlaylistsDAO;
import com.example.laboratorinis.persistence.SongsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class PlaylistsAndSongs implements Serializable {

    @Inject
    private PlaylistsDAO playlistsDAO;

    @Inject
    private SongsDAO songsDAO;

    @Getter @Setter
    private Playlist playlist;

    @Getter @Setter
    private List<Song> allSongs;

    @Getter @Setter
    private Song songToAdd = new Song();

    @PostConstruct
    public void init() {
        this.allSongs = songsDAO.loadAll();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long playlistId = Long.parseLong(requestParameters.get("playlistId"));
        this.playlist = playlistsDAO.findOne(playlistId);
    }

}
