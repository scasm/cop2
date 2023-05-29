package com.example.laboratorinis.services;

import com.example.laboratorinis.entities.Playlist;
import com.example.laboratorinis.persistence.PlaylistsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class SendPlaylistReviewService implements Serializable{
    @Getter
    @Setter
    protected String reviewMessage;

    @Getter
    @Setter
    protected Playlist playlist;

    @Inject
    private PlaylistsDAO playlistsDAO;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long playlistId = Long.parseLong(requestParameters.get("playlistId"));
        this.playlist = playlistsDAO.findOne(playlistId);
    }

    public String sendReview(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println("Sending the review to some server");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Review sent.");
        return "/playListSongs.xhtml?faces-redirect=true&playlistId=" + requestParameters.get("playlistId");
    }

}
