package com.example.laboratorinis.usecases;

import com.example.laboratorinis.mybatis.dao.AuthorMapper;
import com.example.laboratorinis.mybatis.dao.PlaylistMapper;
import com.example.laboratorinis.mybatis.model.Author;
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
    private PlaylistMapper playlistMapper;

    @Getter
    private List<Playlist> allPlaylists;

    @Getter @Setter
    private Playlist playlistToCreate = new Playlist();



    @PostConstruct
    public void init() {
        this.loadAllPlaylists();
    }

    private void loadAllPlaylists() {
        this.allPlaylists = playlistMapper.selectAll();
    }

    @Transactional
    public String createPlaylist() {
        playlistMapper.insert(playlistToCreate);
        return "/index?faces-redirect=true";
    }


}
