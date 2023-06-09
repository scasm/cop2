package com.example.laboratorinis.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.example.laboratorinis.mybatis.model.Playlist;
import com.example.laboratorinis.mybatis.model.Song;
import org.mybatis.cdi.Mapper;
@Mapper
public interface PlaylistMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYLIST
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYLIST
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    int insert(Playlist row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYLIST
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    Playlist selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYLIST
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    List<Playlist> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PLAYLIST
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    int updateByPrimaryKey(Playlist row);

    List<Song> selectSongsForPlaylist(Long id);

    int addSongToPlaylist(Map<String, Long> params);
}