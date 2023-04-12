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

    public String addSongToPlaylist() {
        System.out.println("HELO");
        System.out.println(songToAdd.getId());
        this.songToAdd = songMapper.selectByPrimaryKey(songToAdd.getId());
        System.out.println(this.songToAdd);
        this.playlistToAddSong.getSongs().add(this.songToAdd);

        playlistMapper.updateByPrimaryKey(playlistToAddSong);

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long playlistId = Long.parseLong(requestParameters.get("playlistId"));
        return String.format("/playListSongs?playlistId=%l?faces-redirect=true", playlistId);
    }

}
