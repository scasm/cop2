package com.example.laboratorinis.usecases;

import com.example.laboratorinis.entities.Playlist;
import com.example.laboratorinis.interceptors.LoggedInvocation;
import com.example.laboratorinis.persistence.PlaylistsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdatePlaylistDescription implements Serializable {
    private Playlist playlist;

    @Inject
    private PlaylistsDAO playlistsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long playlistID = Long.parseLong(requestParameters.get("playlistId"));
        this.playlist = playlistsDAO.findOne(playlistID);
    }

    @Transactional
    @LoggedInvocation
    public String update() {
        System.out.println(this.playlist.getVersion() + "versija");
        try {
            playlistsDAO.update(this.playlist);
            System.out.println("Updated !");
        } catch (OptimisticLockException e) {
            System.out.println("Exception !");
            return "/playListSongs.xhtml?faces-redirect=true&playlistId=" + this.playlist.getId() + "&error=optimistic-lock-exception";
        }
        return "playListSongs.xhtml?playlistId=" + this.playlist.getId() + "&faces-redirect=true";
    }
}
