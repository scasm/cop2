package com.example.laboratorinis.usecases;

import com.example.laboratorinis.mybatis.dao.PlaylistMapper;
import com.example.laboratorinis.mybatis.dao.SongMapper;
import com.example.laboratorinis.mybatis.model.Playlist;
import com.example.laboratorinis.mybatis.model.Song;
import lombok.Getter;
import lombok.Setter;
import com.example.laboratorinis.mybatis.dao.AuthorMapper;
import com.example.laboratorinis.mybatis.model.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class SongsMyBatis {
    @Inject
    private SongMapper songMapper;

    @Inject
    private PlaylistMapper playlistMapper;
    @Getter @Setter
    private List<Song> allSongs;

    @Getter @Setter
    private Song songToAdd;

    @Getter @Setter
    private Playlist playlistToAddSong;

    @Getter @Setter
    private List<Song> playlistSongs;


    @PostConstruct
    public void init() {
        this.loadAllSongs();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println(requestParameters.get("playlistId"));
        Long playlistId = Long.parseLong(requestParameters.get("playlistId"));
        this.playlistSongs = playlistMapper.selectSongsForPlaylist(playlistId);
        this.playlistToAddSong = playlistMapper.selectByPrimaryKey(playlistId);
    }

    private void loadAllSongs() {
        this.allSongs = songMapper.selectAll();
    }
    @Transactional
    public String addSongToPlaylist() {

        Song newSong = songMapper.selectByPrimaryKey(Long.valueOf(1));

        List<Song> songs = new ArrayList<Song>();
        songs.add(newSong);
        this.playlistToAddSong.setSongs(songs);

        playlistMapper.updateByPrimaryKey(playlistToAddSong);

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long playlistId = Long.parseLong(requestParameters.get("playlistId"));
        return String.format("/playListSongs?playlistId=%l?faces-redirect=true", playlistId);
    }

}
