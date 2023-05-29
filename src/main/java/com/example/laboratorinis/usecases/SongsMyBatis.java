package com.example.laboratorinis.usecases;

import com.example.laboratorinis.interceptors.LoggedInvocation;
import com.example.laboratorinis.mybatis.dao.PlaylistMapper;
import com.example.laboratorinis.mybatis.dao.SongMapper;
import com.example.laboratorinis.entities.Playlist;
import com.example.laboratorinis.mybatis.model.Song;
import com.example.laboratorinis.persistence.PlaylistsDAO;
import lombok.Getter;
import lombok.Setter;
import com.example.laboratorinis.mybatis.dao.AuthorMapper;
import com.example.laboratorinis.mybatis.model.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model
@SessionScoped
public class SongsMyBatis implements Serializable {
    @Getter
    @Setter
    private Long playlistID;
    @Inject
    private SongMapper songMapper;

    @Inject
    private PlaylistMapper playlistMapper;
    @Getter
    @Setter
    private List<Song> allSongs;

    @Getter
    @Setter
    private List<Song> songsNotInThisPlaylist;
    @Getter
    @Setter
    private Song songToAdd = new Song();

    @Getter
    @Setter
    private Playlist playlist;

    @Getter
    @Setter
    private List<Song> songsInThisPlaylist;

    @Inject
    private PlaylistsDAO playlistsDAO;


    @PostConstruct
    public void init() {
        this.loadAllSongs();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.playlistID = Long.parseLong(requestParameters.get("playlistId"));
        this.playlist = playlistsDAO.findOne(playlistID);
        this.songsInThisPlaylist = songMapper.selectSongsInPlaylist(playlistID);
        this.songsNotInThisPlaylist = songMapper.selectNotInPlaylist(playlistID);
        if(requestParameters.get("songId") != null) {
            songToAdd.setId(Long.parseLong(requestParameters.get("songId")));
        }


    }

    private void loadAllSongs() {
        this.allSongs = songMapper.selectAll();
    }

    @Transactional
    @LoggedInvocation
    public String addSongToPlaylist() {
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("songs_id", songToAdd.getId());
        params.put("playlists_id", playlist.getId());
        playlistMapper.addSongToPlaylist(params);

        return String.format("/playListSongs.xhtml?playlistId=%d&faces-redirect=true", playlist.getId());
    }



}
