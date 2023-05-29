package com.example.laboratorinis.usecases;

import com.example.laboratorinis.interceptors.LoggedInvocation;
import com.example.laboratorinis.services.PlaylistService;
import com.example.laboratorinis.mybatis.model.Playlist;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PlaylistsMyBatis {
    @Inject
    private PlaylistService playlistService;

    @Getter
    private List<Playlist> allPlaylists;

    @Getter @Setter
    private Playlist playlistToCreate = new Playlist();



    @PostConstruct
    public void init() {
        this.loadAllPlaylists();
    }

    private void loadAllPlaylists() {
        this.allPlaylists = playlistService.selectAllPlaylists();
    }

    @Transactional
    @LoggedInvocation
    public String createPlaylist() {
        playlistService.createPlaylist(playlistToCreate);
        return "/index?faces-redirect=true";
    }


}
