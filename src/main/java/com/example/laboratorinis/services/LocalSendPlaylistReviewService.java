package com.example.laboratorinis.services;

import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.Map;

@Specializes
@ViewScoped
public class LocalSendPlaylistReviewService extends SendPlaylistReviewService{
    @Override
    public String sendReview() {
        System.out.println("Review for playlist: " + this.playlist.getName());
        System.out.println("Review message: " + this.reviewMessage);
        return "/playListSongs.xhtml?faces-redirect=true&playlistId=" + this.playlist.getId();
    }
}
