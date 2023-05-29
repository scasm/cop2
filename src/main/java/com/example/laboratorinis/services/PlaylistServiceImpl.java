package com.example.laboratorinis.services;

import com.example.laboratorinis.mybatis.dao.PlaylistMapper;
import com.example.laboratorinis.mybatis.model.Playlist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PlaylistServiceImpl implements PlaylistService{

    @Inject
    private PlaylistMapper playlistMapper;

    @Override
    public List<Playlist> selectAllPlaylists() {
        return playlistMapper.selectAll();
    }

    @Override
    public String createPlaylist(Playlist playlist) {
        playlistMapper.insert(playlist);
        return "/index?faces-redirect=true";
    }
}
