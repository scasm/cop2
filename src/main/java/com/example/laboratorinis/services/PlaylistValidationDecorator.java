package com.example.laboratorinis.services;

import com.example.laboratorinis.mybatis.model.Playlist;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public abstract class PlaylistValidationDecorator implements PlaylistService{

    @Inject @Delegate @Any PlaylistService delegate;

    @Override
    public String createPlaylist(Playlist playlist) {
        if(!containsAtLeastThreeWords(playlist.getDescription())) {
            throw new IllegalArgumentException("Description must contain at least three words");
        }
        return delegate.createPlaylist(playlist);

    }
    public boolean containsAtLeastThreeWords(String input) {
        String[] words = input.split("\\s+");
        return words.length >= 3;
    }
}
