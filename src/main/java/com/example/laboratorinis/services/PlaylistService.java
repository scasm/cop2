package com.example.laboratorinis.services;

import com.example.laboratorinis.mybatis.model.Playlist;

import java.util.List;

public interface PlaylistService {
    String createPlaylist(Playlist playlist);

    List<Playlist> selectAllPlaylists();
}
